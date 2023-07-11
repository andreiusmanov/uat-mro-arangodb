package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
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
    private ComboBox<String> groups;
    private GridListDataView<Ata100Chapter> listDataView;

    public Ata100ChaptersView(Ata100ChaptersService service) {
        super();
        this.service = service;
        grid();
        groups();
        add(grid);

    }

    private void groups() {
        this.groups = new ComboBox<>();
        groups.setItems("ВСЕ ГРУППЫ", "AIRCRAFT GENERAL", "AIRFRAME SYSTEMS", "STRUCTURE", "PROPELLER/ROTOR",
                "POWER PLANT",
                "MISCELLANOUS");
        groups.setValue("ВСЕ ГРУППЫ");
        this.grid.getCrudLayout().addFilterComponent(groups);
        groups.addValueChangeListener(change -> {
            if (change.getValue().equals("ВСЕ ГРУППЫ")) {
                listDataView.removeFilters();
            } else {
                listDataView.setFilter(chapter -> chapter.getGeneral().equals(groups.getValue()));
            }
        });
    }

    private void grid() {
        this.grid = new GridCrud<>(Ata100Chapter.class);
        this.grid.getGrid().setColumns("general", "id", "name");
        this.grid.getGrid().getColumnByKey("general").setHeader("Группа");
        this.grid.getGrid().getColumnByKey("id").setHeader("Номер главы");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование главы");
        this.listDataView = grid.getGrid().getListDataView();

        this.grid.setAddOperation(service::save);
        this.grid.setUpdateOperation(service::save);
        this.grid.setDeleteOperation(service::delete);
        this.grid.setFindAllOperation(service::findAll);

    }
}
