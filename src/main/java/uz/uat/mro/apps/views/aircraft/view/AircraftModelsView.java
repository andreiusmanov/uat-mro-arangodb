package uz.uat.mro.apps.views.aircraft.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.service.AircraftModelService;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;

@PageTitle(value = "Модели ВС")
@Route(value = "aircrafts/models", layout = AircraftLayout.class)
public class AircraftModelsView extends VerticalLayout {
    private AircraftModelService service;
    private GridCrud<AircraftModel> grid;

    public AircraftModelsView(AircraftModelService service) {
        this.service = service;
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(AircraftModel.class);
        grid.getGrid().setColumns("code", "name", "description", "majorModel.name");
        grid.getGrid().getColumnByKey("code").setHeader("Код");
        grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        grid.getGrid().getColumnByKey("description").setHeader("Описание");
        grid.getGrid().getColumnByKey("majorModel.name").setHeader("Основная модель");

        CrudFormFactory<AircraftModel> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("code", "name", "description", "majorModel");
        factory.setFieldCaptions("Код", "Наименование", "Описание", "Основная модель");

        factory.setFieldProvider("majorModel", user -> {
            ComboBox<MajorModel> producers = new ComboBox<>();
            producers.setItems(service.findMajorModels());
            producers.setItemLabelGenerator(e -> e.getName());
            return producers;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());
    }

}
