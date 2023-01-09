package uz.uat.mro.apps.views.aircraft.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.service.MajorModelService;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.views.aircraft.layout.AircraftLayout;


@PageTitle(value = "Основные модели")
@Route(value = "aircrafts/major-models", layout = AircraftLayout.class)
public class MajorModelsView extends VerticalLayout {
private MajorModelService service;

private GridCrud<MajorModel> grid;

    public MajorModelsView(MajorModelService service) {
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
            ComboBox<Firm> producers = new ComboBox<>();
            producers.setItems(service.findProducers());
            producers.setItemLabelGenerator(e -> e.getName());
            return producers;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());
    }
}
