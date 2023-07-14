package uz.uat.mro.apps.views.common.views;

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

import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;
import uz.uat.mro.apps.model.services.organization.OrganizationService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.organization.layouts.OrganizationLayout;

@PageTitle(value = "Отделы")
@Route(value = "organization/departments", layout = OrganizationLayout.class)
public class DepartmentsView extends VerticalLayout {
    private OrganizationService service;
    private Organization organization;
    private OrganizationUnit department;

    private GridCrud<OrganizationUnit> grid;
    private MenuBar menu;
    private MenuItem sectors;

    public DepartmentsView(OrganizationService service) {
        this.service = service;
        this.organization = (Organization) MyUtils.getAttribute("organization");
        grid();
        menu();
        add(new H3("Отделы " + organization.getShortName()), menu, grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);

        this.sectors = menu.addItem("Подразделения");
        sectors.addClickListener(e -> {
            MyUtils.setAttribute("department", department);
            UI.getCurrent().navigate("department/sectors");
        });
        sectors.setEnabled(false);
    }

    private void grid() {
        this.grid = new GridCrud<>(OrganizationUnit.class);
        this.grid.getGrid().setColumns("name", "code", "shortName");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("shortName").setHeader("Кратк. Наименование");

        CrudFormFactory<OrganizationUnit> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("firm", "code", "name", "shortName");
        factory.setFieldCaptions("Организация", "Код", "Наименование", "Аббревиатура");
        factory.setFieldProvider("firm", user -> {
            ComboBox<Organization> cb = new ComboBox<>("Организация", organization);
            cb.setItemLabelGenerator(e -> e.getShortName());
            return cb;
        });

        grid.setAddOperation(service::saveOrganizationUnit);
        grid.setUpdateOperation(service::saveOrganizationUnit);
        grid.setDeleteOperation(service::deleteOrganizationUnit);
        // grid.setFindAllOperation(() ->
        // service.findByFirm(organization.getArangoId()));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            OrganizationUnit dept = new OrganizationUnit();
            // dept.setFirm(organization);
            return dept;
        });

        grid.getGrid().addSelectionListener(e -> {
            this.department = e.getFirstSelectedItem().orElse(null);
            boolean res = e.getFirstSelectedItem().isPresent();
            sectors.setEnabled(res);
        });

    }

}
