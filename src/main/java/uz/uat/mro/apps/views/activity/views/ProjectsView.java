package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.InitialLayout;

@PageTitle(value = "Список проектов")
@Route(value = "activity/projects", layout = InitialLayout.class)
public class ProjectsView extends VerticalLayout {
    private ProjectService service;
    private Grid<Project> grid;
    private MenuBar menu;
    private Project project;

    public ProjectsView(ProjectService service) {
        this.service = service;
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new Grid<>(Project.class);
        this.grid.setItems(service.findAll());
        grid.addSelectionListener(select -> {
            project = grid.getSelectionModel().getFirstSelectedItem().orElse(null);
        });

        grid.setColumns("number", "date", "customer.shortName", "supplier.shortName", "aircraft.regNumber",
                "maintenanceString");
        grid.getColumnByKey("number").setHeader("Номер");
        grid.getColumnByKey("date").setHeader("Дата");
        grid.getColumnByKey("customer.shortName").setHeader("Заказчик");
        grid.getColumnByKey("supplier.shortName").setHeader("Исполнитель");
        grid.getColumnByKey("aircraft.regNumber").setHeader("ВС номер");
        grid.getColumnByKey("maintenanceString").setHeader("Тип ТОиР");
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);

        MenuItem newItem = menu.addItem("Новый");
        newItem.addClickListener(click -> {
            MyUtils.setAttribute(Keys.PROJECT, grid.getSelectionModel().getFirstSelectedItem());
            Notification.show("Дальше пойдет на страницу Проекта");
        });
        menu.addItem("Редактировать");

    }

}
