package uz.uat.mro.apps.views.organization.views;

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

import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.organization.OrganizationService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Организации")
@Route(value = "common/organizations", layout = AdminLayout.class)
public class OrganizationsView extends VerticalLayout {
    private OrganizationService service;
    private GridCrud<Organization> grid;
    private MenuBar menu;
    private MenuItem departmentItem;
    private Organization organization;

    public OrganizationsView(OrganizationService service) {
        this.service = service;
        menu();
        grid();
        add(new H3("Организации"), grid);
    }

   

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        departmentItem = menu.addItem("Обзор организации");
        departmentItem.setEnabled(false);
        departmentItem.addClickListener(e -> {
            MyUtils.setAttribute("organization", organization);
            UI.getCurrent().navigate("organizations/organization");
        });
    }

    private void grid() {
        this.grid = new GridCrud<>(Organization.class);
        List<Organization> list = service.findAllOrganizations();
        this.grid.getGrid().setItems(list);
        this.grid.getGrid().setColumns("code", "name", "shortName", "country.shortName");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("shortName").setHeader("Аббрев.");
        this.grid.getGrid().getColumnByKey("country.shortName").setHeader("Страна");

        CrudFormFactory<Organization> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("country", "code", "name", "shortName");
        factory.setFieldCaptions("Страна", "Код", "Наименование", "Аббревиатура");

        factory.setFieldProvider("country", user -> {
            ComboBox<Country> countries = new ComboBox<>();
            countries.setItems(service.findAllCountries());
            countries.setItemLabelGenerator(e -> e.getShortName());
            return countries;
        });

        grid.setAddOperation(service::saveOrganization);
        grid.setUpdateOperation(service::saveOrganization);
        grid.setDeleteOperation(service::deleteOrganization);
        grid.setFindAllOperation(service::findAllOrganizations);

        grid.getCrudLayout().addToolbarComponent(menu);

        grid.getGrid().addSelectionListener(e -> {
            organization = e.getFirstSelectedItem().orElse(null);
            boolean res = e.getFirstSelectedItem().isPresent();
            departmentItem.setEnabled(res);
        });
    }

}
