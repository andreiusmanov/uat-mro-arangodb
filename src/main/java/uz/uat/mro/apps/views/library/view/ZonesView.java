package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.services.aircraft.AircraftZoneService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Зоны ВС")
@Route(value = "mpd/zones", layout = MpdLayout.class)
public class ZonesView extends VerticalLayout {
    private AircraftZoneService service;
    private GridCrud<AircraftZone> grid;
    private MpdEdition edition;
    private MajorModel model;
    private MenuBar menu;

    public ZonesView(AircraftZoneService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        this.model = edition.getModel();

        grid();
        add(grid);
    }

        private void grid() {
        this.grid = new GridCrud<>(AircraftZone.class);
        this.grid.getGrid().setColumns("code", "name", "model.name");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("model.name").setHeader("Модель ВС");

        grid.setAddOperation(service::saveZone);
        grid.setUpdateOperation(service::saveZone);
        grid.setDeleteOperation(service::deleteZone);
        grid.setFindAllOperation(() -> service.getAllZonesByModel(model));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            AircraftZone zone = new AircraftZone();
            zone.setModel(model);
            return zone;
        });
        CrudFormFactory<AircraftZone> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("model", "code", "name");
        factory.setFieldCaptions("Модель ВС", "Код зоны", "Наименование зоны");

        factory.setFieldProvider("model", element -> {
            ComboBox<MajorModel> c = new ComboBox<>("Модель ВС", model);
            c.setItemLabelGenerator(e -> e.getName());
            return c;
        });
    }
}
