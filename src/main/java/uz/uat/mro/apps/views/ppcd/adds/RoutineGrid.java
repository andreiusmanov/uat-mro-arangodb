package uz.uat.mro.apps.views.ppcd.adds;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.docs.MaintenanceCard;
import uz.uat.mro.apps.model.marketing.entity.Project;

public class RoutineGrid extends VerticalLayout {
    private MaintenanceCardsService service;
    private Project project;
    private Grid<MaintenanceCard> grid;
    private MenuBar menu;

    public RoutineGrid(Project project, MaintenanceCardsService service) {
        this.project = project;
        this.service = service;
        grid();
        menu();
        add(menu, grid);

    }

    private void menu() {
        menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
    }

    private void grid() {
        this.grid = new Grid<>(MaintenanceCard.class);
        grid.setSelectionMode(SelectionMode.MULTI);
        this.grid.setItems(service.findAllByProject(project.getArangoId()));
        this.grid.setColumns("number", "taskcardString", "description", "remarks");

    }
}
