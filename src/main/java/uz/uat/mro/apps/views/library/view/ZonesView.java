package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.service.MpdZonesService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Зоны ВС")
@Route(value = "mpd/zones", layout = MpdLayout.class)
public class ZonesView extends VerticalLayout {
    private MpdZonesService service;
    private GridCrud<MpdZone> grid;
    private MpdEdition edition;
    private MajorModel model;
    private MenuBar menu;

    public ZonesView(MpdZonesService service) {
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
        menu.addItem("Импорт данных из MPD", e -> {

        });
    }

    private void grid() {
        this.grid = new GridCrud<>(MpdZone.class);
        this.grid.getGrid().setColumns("code", "name", "model.name");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("model.name").setHeader("Модель ВС");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findZoneByModel(model.getArangoId()));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            MpdZone zone = new MpdZone();
            zone.setModel(model);
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
