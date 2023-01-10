package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.service.MpdZonesService;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Зоны ВС")
@Route(value = "mpd/zones", layout = MpdLayout.class)
public class ZonesView extends VerticalLayout {
    private MpdZonesService service;
    private GridCrud<MpdZone> grid;
    private ComboBox<MajorModel> models;
    private MajorModel model;
    private MenuBar menu;


    public ZonesView(MpdZonesService service) {
        this.service = service;
        models();
        grid();
    }

    private void models() {
        this.models = new ComboBox<>("Модель ВС");
        models.setItems(service.models());
        models.setItemLabelGenerator(MajorModel::getName);
        models.addValueChangeListener(e -> {
            this.model = e.getValue();
            GridListDataView<MpdZone> provider = grid.getGrid().getListDataView();
            provider.addFilter(arg0 -> arg0.getModel().equals(e.getValue()));

        });
    }

    private void grid() {
        this.grid = new GridCrud<>(MpdZone.class);
        this.grid.getGrid().setColumns("code", "name");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("model").setHeader("Модель ВС");

        grid.getCrudLayout().addFilterComponent(models);

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findZoneByModel(models.getValue()));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            MpdZone zone = new MpdZone(model);
            return zone;
        });
        CrudFormFactory<MpdZone> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("model", "code", "name");
        factory.setFieldCaptions("Модель ВС", "Код зоны", "Наименование зоны");

        factory.setFieldProvider("model", element -> {
            ComboBox<MajorModel> c = new ComboBox<>("Модель ВС", model);
            c.setItemLabelGenerator(e -> e.getName());
            return c;
        });
    }
}
