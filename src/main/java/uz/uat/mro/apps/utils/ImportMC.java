package uz.uat.mro.apps.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.text.PDFTextStripper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;
import uz.uat.mro.apps.model.activity.entity.MaintenanceTaskcard;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdEdition;

public class ImportMC {

    /**
     * import data from xls
     * 
     * @param fileName
     * @param project
     * @throws CsvException
     * @throws IOException
     */
    public static void importMaintenanceCards(MaintenanceCardsService service, String fileName, Project project)
            throws IOException, CsvException {
        if (fileName.isBlank()) {
            return;
        }

        MpdEdition edition = project.getEdition();

        List<String[]> cards = normalizeCards(fileName);
        List<MaintenanceCard> list = new ArrayList<>();

        List<TaskGroup> taskGroups = service.findAllTaskgroups();

        cards.stream().forEach(card -> {
            MaintenanceCard m = new MaintenanceCard();
            m.setTaskGroup((TaskGroup) findObjectByProperty(taskGroups, "name", card[0]));
            m.setSequence(card[1]);
            m.setNumber(card[2]);
            m.setDescription(card[3]);
            m.setRemarks(card[4]);
            m.setValid(true);
            if (m.getTaskGroup().getId().equals("ht") || m.getTaskGroup().getId().equals("routine")) {
                m.setTaskcard(service.findTaskcardByNumberAndEdition(card[2], edition));
            }
            m.setTaskcardString(m.getTaskcard().getNumber());
            m.setProject(project);
            list.add(m);
        });

        service.saveAll(list);

    }

    private static List<String[]> normalizeCards(String fileName) throws IOException, CsvException {
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    private static <T> T findObjectByProperty(List<T> list, String propertyName, Object propertyValue) {
        for (T object : list) {
            try {
                Field field = object.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                Object value = field.get(object);
                if (value.equals(propertyValue)) {
                    return object;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void importCards(MaintenanceCardsService service, String directoryName, Project project)
            throws IOException {
        if (directoryName.isBlank()) {
            return;
        }
        File dirName = new File(directoryName);
        if (dirName.isDirectory()) {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".pdf");
                }
            };
            String[] files = dirName.list(filter);

            for (String file : files) {
                PDDocument document = PDDocument.load(new File(directoryName + "/" + file));
                PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();

                if (outline != null) {
                    List<PDOutlineItem> itemsMain = getItems(outline);

                    for (PDOutlineItem item : itemsMain) {
                        PDPage page = item.findDestinationPage(document);

                        System.out.println(
                                "Title: " + item.getTitle() + ", Page number: " + document.getPages().indexOf(page));
                        List<PDOutlineItem> items = getItems(item);
                        for (PDOutlineItem itemSec : items) {
                            PDPage pageSec = itemSec.findDestinationPage(document);
                            int pageNumber = document.getPages().indexOf(pageSec) + 1;
                            Pattern pattern = Pattern.compile("\\d{2}-\\d{3}-\\d{2}-\\d{2}");
                            String input = itemSec.getTitle();
                            Matcher matcher = pattern.matcher(input);
                            boolean isMatch = matcher.find();

                            if (isMatch) {
                                PDFTextStripper stripper = new PDFTextStripper();
                                stripper.setStartPage(pageNumber);
                                stripper.setEndPage(pageNumber);
                                stripper.setSortByPosition(false);
                                String input2 = stripper.getText(document).replaceAll("\n|\r", "_");
                                Pattern pattern2 = Pattern
                                        .compile("CARD NO._(.+)_DATE.+AREA_(.+)_VERSION.+SKILL_(.+)_AIRPLANE_");
                                Matcher matcher2 = pattern2.matcher(input2);
                                boolean isMatch2 = matcher2.find();
                                if (isMatch2) {
                                    String cardNo = matcher2.group(1).trim();
                                    String area = matcher2.group(2).trim();
                                    String skill = matcher2.group(3).trim();
                                    System.out.println(cardNo);
                                    List<MaintenanceCard> mcOptional = service.findMaintenanceCardByNumber(cardNo,
                                            project);
                                    if (!mcOptional.isEmpty()) {
                                        for (MaintenanceCard c : mcOptional) {
                                            MaintenanceTaskcard mtc = new MaintenanceTaskcard();
                                            mtc.setProject(project);
                                            mtc.setNumber(cardNo);
                                            mtc.setWorkArea(area);
                                            mtc.setSkill(skill);
                                            c.setManifacturersTaskcard(mtc);
                                            service.save(c);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                document.close();

            }
        }

    }

    public static void linkAccesses(MaintenanceCardsService service, Project project) {
        MpdEdition edition = project.getEdition();
        List<MpdAccess> accesses = service.findByModel(edition.getModel().getArangoId());
        for (MpdAccess access : accesses) {

        }

    }

    private static List<PDOutlineItem> getItems(PDOutlineItem parent) {
        return StreamSupport.stream(parent.children().spliterator(), false).toList();
    }

    private static List<PDOutlineItem> getItems(PDDocumentOutline parent) {
        return StreamSupport.stream(parent.children().spliterator(), false).toList();
    }
}
