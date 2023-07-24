package uz.uat.mro.apps.views.library.view;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.services.aircraft.AircraftZoneService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Субзоны")
@Route(value = "mpd/subzones", layout = MpdLayout.class)
public class SubzonesView extends VerticalLayout {
    private AircraftZoneService service;
    private GridCrud<AircraftSubzone> grid;
    private MpdEdition edition;
    private MajorModel model;
    private MenuBar menu;
    private GridListDataView<AircraftSubzone> listDataView;

    public SubzonesView(AircraftZoneService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        this.model = edition.getModel();
        grid();
        menu();
        add(menu, grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        List<AircraftZone> zones = service.getAllZonesByModel(model);

        MenuItem allZones = menu.addItem("Все Зоны");
        allZones.addClickListener(e -> listDataView.removeFilters());

        for (AircraftZone zone : zones) {
            menu.addItem(zone.getCode(), e -> {
                listDataView.setFilter(subzone -> subzone.getZone().equals(zone));
            });
        }

    }

    private void grid() {
        this.grid = new GridCrud<>(AircraftSubzone.class);
        this.grid.getGrid().setColumns("code", "name", "zone.code", "model.name");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("zone.code").setHeader("Зона ВС");
        this.grid.getGrid().getColumnByKey("model.name").setHeader("Модель ВС");

        grid.setAddOperation(service::saveSubzone);
        grid.setUpdateOperation(service::saveSubzone);
        grid.setDeleteOperation(service::deleteSubzone);
        grid.setFindAllOperation(() -> service.getAllSubzonesByModel(model));

        // filter
        listDataView = grid.getGrid().getListDataView();

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            AircraftSubzone subzone = new AircraftSubzone();
            subzone.setModel(model);
            return subzone;
        });
        CrudFormFactory<AircraftSubzone> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("model", "zone", "code", "name", "description");
        factory.setFieldCaptions("Модель ВС", "Код зоны", "Код субзоны", "Наименование", "Описание");

        factory.setFieldProvider("model", element -> {
            ComboBox<MajorModel> c = new ComboBox<>("Модель ВС", model);
            c.setItemLabelGenerator(e -> e.getName());
            return c;
        });

        factory.setFieldProvider("zone", element -> {
            ComboBox<MajorModel> c = new ComboBox<>("Код зоны", e -> service.getAllZonesByModel(model));
            c.setItemLabelGenerator(e -> e.getCode());
            return c;
        });

    }

}
