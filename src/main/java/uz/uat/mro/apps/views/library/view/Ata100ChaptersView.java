package uz.uat.mro.apps.views.library.view;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.library.entity.Ata100Chapter;
import uz.uat.mro.apps.model.library.service.Ata100ChaptersService;
import uz.uat.mro.apps.views.library.layout.LibraryLayout;

@PageTitle(value = "ATA 100 Chapters")
@Route(value = "ATA100", layout = LibraryLayout.class)
public class Ata100ChaptersView extends VerticalLayout {
    private Ata100ChaptersService service;
    private GridCrud<Ata100Chapter> grid;
    private Button b = new Button("import");

    public Ata100ChaptersView(Ata100ChaptersService service) {
        super();
        this.service = service;
        grid();
        button();
        add(b, grid);

    }

    private void button() {
        b.addClickListener(click -> {
            try {
                loadAndSaveChapters();
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void grid() {
        this.grid = new GridCrud<>(Ata100Chapter.class);
        this.grid.getGrid().setColumns("general", "id", "name");
        this.grid.getGrid().getColumnByKey("general").setHeader("Общее назначение");
        this.grid.getGrid().getColumnByKey("id").setHeader("Номер главы");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование главы");

        this.grid.setAddOperation(service::save);
        this.grid.setUpdateOperation(service::save);
        this.grid.setDeleteOperation(service::delete);
        this.grid.setFindAllOperation(service::findAll);

    }

    private void loadAndSaveChapters() throws IOException, CsvValidationException {
        String filePath = "/home/andreyu/Downloads/ata-100-chapters.csv";
        List<String[]> list = new ArrayList<>();
        List<Ata100Chapter> chapters = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            for (String[] strings : list) {
                Ata100Chapter chapter = new Ata100Chapter();
                chapter.setGeneral(strings[0].trim());
                chapter.setId(strings[1].replace("ATA", "").trim());
                chapter.setName(strings[2].trim());
                chapters.add(chapter);
            }
            service.saveAll(chapters);
        }

    }

}
