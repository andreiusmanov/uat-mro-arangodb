package uz.uat.mro.apps.views.aircraft.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.aircraft.AircraftService;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;

@PageTitle(value = "Основные модели")
@Route(value = "aircrafts/major-models", layout = AircraftLayout.class)
public class MajorModelsView extends VerticalLayout {
    private AircraftService service;

    private GridCrud<MajorModel> grid;

    public MajorModelsView(AircraftService service) {
        this.service = service;
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(MajorModel.class);
        grid.getGrid().setColumns("code", "name", "description", "producer.shortName");
        grid.getGrid().getColumnByKey("code").setHeader("Код");
        grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        grid.getGrid().getColumnByKey("description").setHeader("Описание");
        grid.getGrid().getColumnByKey("producer.shortName").setHeader("Производитель");

        CrudFormFactory<MajorModel> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("code", "name", "description", "producer");
        factory.setFieldCaptions("Код", "Наименование", "Описание", "Производитель");

        factory.setFieldProvider("producer", user -> {
            ComboBox<Organization> producers = new ComboBox<>();
            producers.setItems(service.findAllOrganizations());
            producers.setItemLabelGenerator(e -> e.getName());
            return producers;
        });

        grid.setAddOperation(service::saveMajorModel);
        grid.setUpdateOperation(service::saveMajorModel);
        grid.setDeleteOperation(service::deleteMajorModel);
        grid.setFindAllOperation(() -> service.findAllMajorModels());
    }
}
