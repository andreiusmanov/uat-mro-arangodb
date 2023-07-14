package uz.uat.mro.apps.views.aircraft.view;

import java.util.Optional;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.service.AircraftsService;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;

@PageTitle(value = "Воздушные суда")
@Route(value = "aircrafts/aircrafts", layout = AircraftLayout.class)
public class AircraftsView extends VerticalLayout {
    private AircraftsService service;
    private GridCrud<Aircraft> grid;

    public AircraftsView(AircraftsService service) {
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
            models.setItems(service.findModels());
            models.setItemLabelGenerator(e -> e.getName());
            return models;
        });
        factory.setFieldProvider("airline", user -> {
            ComboBox<Firm> airlines = new ComboBox<>();
            airlines.setItems(service.findAirlineFirms());
            airlines.setItemLabelGenerator(e -> e.getName());
            return airlines;
        });
        factory.setFieldProvider("owner", user -> {
            ComboBox<Firm> owners = new ComboBox<>();
            owners.setItems(service.findOwnerFirms());
            owners.setItemLabelGenerator(e -> e.getName());
            return owners;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);
    }
}
