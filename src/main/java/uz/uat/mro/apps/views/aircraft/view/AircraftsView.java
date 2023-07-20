package uz.uat.mro.apps.views.aircraft.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.aircraft.AircraftModel;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.aircraft.AircraftService;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;

@PageTitle(value = "Воздушные суда")
@Route(value = "aircrafts/aircrafts", layout = AircraftLayout.class)
public class AircraftsView extends VerticalLayout {
    private AircraftService service;
    private GridCrud<Aircraft> grid;

    public AircraftsView(AircraftService service) {
        this.service = service;
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Aircraft.class);
        grid.getGrid().setColumns("regNumber", "model.name", "airline.shortName", "owner.shortName");
        grid.getGrid().getColumnByKey("regNumber").setHeader("Рег. Номер");
        grid.getGrid().getColumnByKey("model.name").setHeader("Модель ВС");
        grid.getGrid().getColumnByKey("airline.shortName").setHeader("Авиалиния");
        grid.getGrid().getColumnByKey("owner.shortName").setHeader("Владелец");

        CrudFormFactory<Aircraft> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("regNumber", "model", "airline", "owner");
        factory.setFieldCaptions("Рег. Номер", "Модель ВС", "Авиалиния", "Владелец");

        factory.setFieldProvider("model", user -> {
            ComboBox<AircraftModel> models = new ComboBox<>();
            models.setItems(service.findAllAircraftModels());
            models.setItemLabelGenerator(e -> e.getName());
            return models;
        });
        factory.setFieldProvider("airline", user -> {
            ComboBox<Organization> airlines = new ComboBox<>();
            airlines.setItems(service.findAirlineFirms());
            airlines.setItemLabelGenerator(e -> e.getName());
            return airlines;
        });
        factory.setFieldProvider("owner", user -> {
            ComboBox<Organization> owners = new ComboBox<>();
            owners.setItems(service.findAirlineFirms());
            owners.setItemLabelGenerator(e -> e.getName());
            return owners;
        });

        grid.setAddOperation(service::saveAircraft);
        grid.setUpdateOperation(service::saveAircraft);
        grid.setDeleteOperation(service::deleteAircraft);
        grid.setFindAllOperation(service::findAllAircrafts);
    }
}
