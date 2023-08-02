package uz.uat.mro.apps.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

import uz.uat.mro.apps.model.activity.entity.MaintenanceTaskcard;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.model.ppcd.entity.ImportedCard;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;
import uz.uat.mro.apps.model.ppcd.service.ImportedCardsService;

public class ImportMC {

    public static void importRevisionCards(ImportedCardsService service, InputStream file, Revision revision)
            throws IOException, CsvException {
        if (file.available() == 0) {
            return;
        }
        List<String[]> cards = normalizeCards2(file);
        List<ImportedCard> list = new ArrayList<>();

        cards.stream().forEach(card -> {
            ImportedCard m = new ImportedCard();
            m.setProject(revision.getProject());
            m.setRevision(revision);
            m.setAction(card[0]);
            m.setTaskGroup(card[1]);
            m.setRevisionNumber(card[2]);
            m.setSequence(card[3]);
            m.setNumber(card[4]);
            m.setFunction(card[5]);
            m.setMhrs(card[6]);
            m.setDescription(card[7]);
            m.setRemarks(card[8]);
            m.setStatus("imported");
            list.add(m);
        });

        service.saveAll(list);
    }

    public static void convertImportedCards2MaintenanceCards(ImportedCardsService service1,
            MaintenanceCardsService service2,
            Revision revision, Project projectt) {

        Project project = projectt;
        List<ImportedCard> cards = service1.findByRevision(revision);
        MpdEdition edition = project.getEdition();
        List<MaintenanceCard> list = new ArrayList<>();
        List<TaskGroup> taskGroups = service2.findAllTaskgroups();

        cards.stream().forEach(card -> {

            String res = card.getAction();
            switch (res) {
                case "ADD": {
                    MaintenanceCard m = new MaintenanceCard();
                    m.setTaskGroup((TaskGroup) findObjectByProperty(taskGroups, "name", card.getTaskGroup()));
                    m.setProject(project);
                    m.setRevision(revision);
                    m.setSequence(card.getSequence());
                    m.setMhrs(card.getMhrs());
                    m.setNumber(card.getNumber());
                    m.setDescription(card.getDescription());
                    m.setRemarks(card.getRemarks());
                    if (m.getTaskGroup().getId().equals("ht") || m.getTaskGroup().getId().equals("routine")) {
                        Optional<MpdTaskcard> opt = service2.findTaskcardByNumberAndEdition(card.getNumber(), edition);
                        if (opt.isPresent()) {
                            m.setTaskcard(opt.get());
                            m.setTaskcardString(m.getTaskcard().getNumber());
                        }
                    }
                    m.setStatus("active");
                    list.add(m);
                    break;
                }

                case ("REMOVE"): {
                    String sequence = card.getSequence();
                    String number = card.getNumber();
                    MaintenanceCard m = service2.findMaintenanceCard(project, sequence, number);
                    m.setStatus("removed");
                    m.setRevision(revision);
                    list.add(m);
                    break;
                }
                case ("UPDATE"): {
                    String sequence = card.getSequence();
                    String number = card.getNumber();
                    MaintenanceCard m = service2.findMaintenanceCard(project, sequence, number);

                    m.setTaskGroup((TaskGroup) findObjectByProperty(taskGroups, "name", card.getTaskGroup()));
                    m.setProject(project);
                    m.setRevision(revision);
                    m.setSequence(card.getSequence());
                    m.setMhrs(card.getMhrs());
                    m.setNumber(card.getNumber());
                    m.setDescription(card.getDescription());
                    m.setRemarks(card.getRemarks());
                    if (m.getTaskGroup().getId().equals("ht") || m.getTaskGroup().getId().equals("routine")) {
                        Optional<MpdTaskcard> opt = service2.findTaskcardByNumberAndEdition(card.getNumber(), edition);
                        if (opt.isPresent()) {
                            m.setTaskcard(opt.get());
                            m.setTaskcardString(m.getTaskcard().getNumber());
                        }
                    }
                    m.setStatus("active");
                    list.add(m);
                    break;
                }
            }
            service2.saveAll(list);
        });

    }

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
            m.setRevision(service.findRevision(card[1], project).get());
            m.setSequence(card[2]);
            m.setTaskCode(card[3]);
            m.setMhrs(card[4]);
            m.setNumber(card[5]);
            m.setDescription(card[6]);
            m.setRemarks(card[7]);
            if (m.getTaskGroup().getId().equals("ht") || m.getTaskGroup().getId().equals("routine")) {
                Optional<MpdTaskcard> opt = service.findTaskcardByNumberAndEdition(card[2], edition);
                if (opt.isPresent()) {
                    m.setTaskcard(opt.get());
                    m.setTaskcardString(m.getTaskcard().getNumber());
                }
            }
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

    private static List<String[]> normalizeCards2(InputStream file) throws IOException, CsvException {
        try (
                InputStreamReader streamReader = new InputStreamReader(file);
                Reader reader = new BufferedReader(streamReader)) {
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
                                            // c.setManifacturersTaskcard(mtc);
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
        List<AircraftAccess> accesses = service.findByModel(edition.getModel());
        for (AircraftAccess access : accesses) {

        }

    }

    private static List<PDOutlineItem> getItems(PDOutlineItem parent) {
        return StreamSupport.stream(parent.children().spliterator(), false).toList();
    }

    private static List<PDOutlineItem> getItems(PDDocumentOutline parent) {
        return StreamSupport.stream(parent.children().spliterator(), false).toList();
    }
}
