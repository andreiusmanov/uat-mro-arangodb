package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.service.FirmsService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Организация")
@Route(value = "common/firms", layout = AdminLayout.class)
public class FirmsView extends VerticalLayout {
    private FirmsService service;
    private GridCrud<Firm> grid;
    private MenuBar menu;
    MenuItem departmentItem;
    private Firm firm;

    public FirmsView(FirmsService service) {
        this.service = service;
        grid();
        menu();
        add(new H3("Организации"), menu, grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        departmentItem = menu.addItem("Отделы");
        departmentItem.setEnabled(false);
        departmentItem.addClickListener(e -> {
            MyUtils.setAttribute("firm", firm);
            UI.getCurrent().navigate("firm/departments");
        });
    }

    private void grid() {
        this.grid = new GridCrud<>(Firm.class);
        List<Firm> list = service.findFirms();
        this.grid.getGrid().setItems(list);
        this.grid.getGrid().setColumns("code", "name", "shortName", "country.shortName");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("shortName").setHeader("Аббрев.");
        this.grid.getGrid().getColumnByKey("country.shortName").setHeader("Страна");

        CrudFormFactory<Firm> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("country", "code", "name", "shortName");
        factory.setFieldCaptions("Страна", "Код", "Наименование", "Аббревиатура");

        factory.setFieldProvider("country", user -> {
            ComboBox<Country> countries = new ComboBox<>();
            countries.setItems(service.findAllCountries());
            countries.setItemLabelGenerator(e -> e.getShortName());
            return countries;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findFirms);

        grid.getGrid().addSelectionListener(e -> {
            firm = e.getFirstSelectedItem().orElse(null);
            boolean res = e.getFirstSelectedItem().isPresent();
            departmentItem.setEnabled(res);
        });
    }

}
