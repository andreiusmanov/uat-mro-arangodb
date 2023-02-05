package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;

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

    public Ata100ChaptersView(Ata100ChaptersService service) {
        super();
        this.service = service;
        grid();

    }

    private void grid() {
        this.grid = new GridCrud<>(Ata100Chapter.class);
        this.grid.getGrid().setColumns("general", "number", "name");
        this.grid.getGrid().getColumnByKey("general").setHeader("Общее назначение");
        this.grid.getGrid().getColumnByKey("number").setHeader("Номер главы");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование главы");

        this.grid.setAddOperation(service::save);
        this.grid.setUpdateOperation(service::save);
        this.grid.setDeleteOperation(service::delete);
        this.grid.setFindAllOperation(service::findAll);

    }

}
