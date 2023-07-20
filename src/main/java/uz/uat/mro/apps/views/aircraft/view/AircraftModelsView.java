package uz.uat.mro.apps.views.aircraft.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.AircraftModel;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.services.aircraft.AircraftService;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;

@PageTitle(value = "Модели ВС")
@Route(value = "aircrafts/models", layout = AircraftLayout.class)
public class AircraftModelsView extends VerticalLayout {
    private AircraftService service;
    private GridCrud<AircraftModel> grid;

    public AircraftModelsView(AircraftService service) {
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
            producers.setItems(service.findAllMajorModels());
            producers.setItemLabelGenerator(e -> e.getName());
            return producers;
        });

        grid.setAddOperation(service::saveAircraftModel);
        grid.setUpdateOperation(service::saveAircraftModel);
        grid.setDeleteOperation(service::deleteAircraftModel);
        grid.setFindAllOperation(() -> service.findAllAircraftModels());
    }

}
