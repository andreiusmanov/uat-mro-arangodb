package uz.uat.mro.apps.views.ppcd.views;

import java.security.Provider.Service;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.ppcd.adds.RoutineGrid;
import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("Рабочие карты")
@Route(value = "ppcd/jobcards", layout = PlanningLayout.class)
public class JobcardsView extends VerticalLayout {
    private MenuBar menu;
    private MenuBar actionsMenu;
    private TextField searchField;
    private Div div;
    private Project project;
    private MaintenanceCardsService service;

    /**
     * 
     */
    public JobcardsView(MaintenanceCardsService service) {
        this.service = service;
        project = (Project) MyUtils.getAttribute("project");
        menu();
        actionsMenu();
        searchField();
        div();
        add(menu, searchField, actionsMenu, div);
    }

    private void div() {
        this.div = new Div();
        div.setSizeFull();
    }

    private void actionsMenu() {
        this.actionsMenu = new MenuBar();
        actionsMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        actionsMenu.addItem("Открыть");
        actionsMenu.addItem("Отложить");
        actionsMenu.addItem("Закрыть");
    }

    private void searchField() {
        this.searchField = new TextField("", "", "Поиск");
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem routine = menu.addItem("Рабочие карты");
        routine.addClickListener(e -> {
            RoutineGrid grid = new RoutineGrid(project, service);
            this.div.add(grid);
        });
        menu.addItem("Non-routine карты");
        menu.addItem("EO карты");
    }

}
