package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.InitialLayout;

@PageTitle(value = "Список проектов")
@Route(value = "activity/projects", layout = InitialLayout.class)
public class ProjectsView extends VerticalLayout {
    private ProjectService service;
    private GridCrud<Project> grid;
    private MenuBar menu;
    private MenuItem dataItem;
    private Project project;

    public ProjectsView(ProjectService service) {
        this.service = service;
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Project.class);
        grid.getGrid().addSelectionListener(select -> {
            project = grid.getGrid().getSelectionModel().getFirstSelectedItem().orElse(null);
            dataItem.setEnabled(!grid.getGrid().getSelectedItems().isEmpty());
        });

        grid.getGrid().setColumns("number", "date", "customer.shortName", "supplier.shortName", "aircraft.regNumber",
                "maintenanceString");
        grid.getGrid().getColumnByKey("number").setHeader("Номер");
        grid.getGrid().getColumnByKey("date").setHeader("Дата");
        grid.getGrid().getColumnByKey("customer.shortName").setHeader("Заказчик");
        grid.getGrid().getColumnByKey("supplier.shortName").setHeader("Исполнитель");
        grid.getGrid().getColumnByKey("aircraft.regNumber").setHeader("ВС номер");
        grid.getGrid().getColumnByKey("maintenanceString").setHeader("Тип ТОиР");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);

        CrudFormFactory<Project> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("number", "date", "customer", "supplier", "aircraft", "startDate", "endDate",
                "maintenanceString");
        factory.setFieldCaptions("Номер", "Дата", "Заказчик", "Исполнитель", "ВС номер", "Дата начала",
                "Дата окончания", "Виды работ");

        factory.setFieldProvider("customer", user -> {
            ComboBox<Firm> customers = new ComboBox<>();
            customers.setItems(service.findAllCustomers());
            customers.setItemLabelGenerator(e -> e.getShortName());
            return customers;
        });
        factory.setFieldProvider("supplier", user -> {
            ComboBox<Firm> suppliers = new ComboBox<>();
            suppliers.setItems(service.findAllSuppliers());
            suppliers.setItemLabelGenerator(e -> e.getShortName());
            return suppliers;
        });

        factory.setFieldProvider("aircraft", user -> {
            ComboBox<Aircraft> aircrafts = new ComboBox<>();
            aircrafts.setItems(service.findAllAircrafts());
            aircrafts.setItemLabelGenerator(e -> e.getRegNumber());
            return aircrafts;
        });

    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        dataItem = menu.addItem("Данные", click -> {
            MyUtils.setAttribute(Keys.PROJECT, project);
            UI.getCurrent().navigate(ProjectView.class);
        });
    }

}
