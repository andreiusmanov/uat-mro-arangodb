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

import uz.uat.mro.apps.model.common.entity.Department;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.service.DepartmentService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.FirmLayout;

@PageTitle(value = "Отделы")
@Route(value = "firm/departments", layout = FirmLayout.class)
public class DepartmentsView extends VerticalLayout {
    private DepartmentService service;
    private Firm firm;
    private Department department;

    private GridCrud<Department> grid;
    private MenuBar menu;
    private MenuItem sectors;

    public DepartmentsView(DepartmentService service) {
        this.service = service;
        this.firm = (Firm) MyUtils.getAttribute("firm");
        grid();
        menu();
        add(new H3("Отделы " + firm.getShortName()), menu, grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);

        this.sectors = menu.addItem("Участки/Бригады");
        sectors.addClickListener(e -> {
            MyUtils.setAttribute("department", department);
            UI.getCurrent().navigate("department/sectors");
        });
        sectors.setEnabled(false);
    }

    private void grid() {
        this.grid = new GridCrud<>(Department.class);
        this.grid.getGrid().setColumns("name", "code", "shortName");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("shortName").setHeader("Кратк. Наименование");

        CrudFormFactory<Department> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("firm", "code", "name", "shortName");
        factory.setFieldCaptions("Организация", "Код", "Наименование", "Аббревиатура");
        factory.setFieldProvider("firm", user -> {
            ComboBox<Firm> cb = new ComboBox<>("Организация", firm);
            cb.setItemLabelGenerator(e -> e.getShortName());
            return cb;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findByFirm(firm.getArangoId()));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            Department dept = new Department();
            dept.setFirm(firm);
            return dept;
        });

        grid.getGrid().addSelectionListener(e -> {
            this.department = e.getFirstSelectedItem().orElse(null);
            boolean res = e.getFirstSelectedItem().isPresent();
            sectors.setEnabled(res);
        });

    }

}
