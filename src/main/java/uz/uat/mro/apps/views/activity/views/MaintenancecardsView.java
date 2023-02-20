package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "LOV MC")
@Route(value = "activity/maintenancecards", layout = ProjectLayout.class)
public class MaintenancecardsView extends VerticalLayout {

    private ProjectService service;
    private Project project;
    private GridCrud<MaintenanceCard> grid;
    private MenuBar menu;
    private MenuItem allMc;
    private MenuItem routineMc;
    private MenuItem htMc;
    private MenuItem eoMc;

    public MaintenancecardsView(ProjectService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        menu();
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(MaintenanceCard.class);
        grid.setAddOperation(service::saveCard);
        grid.setUpdateOperation(service::saveCard);
        grid.setDeleteOperation(service::deleteCard);
        grid.setFindAllOperation(() -> service.findAllCards(project.getArangoId()));
        grid.getGrid().setColumns("number", "mpReference", "taskcardString", "description", "remarks");
        grid.getGrid().getColumnByKey("number").setHeader("Number");
        grid.getGrid().getColumnByKey("mpReference").setHeader("MP Reference");
        grid.getGrid().getColumnByKey("taskcardString").setHeader("MPD Taskcard");
        grid.getGrid().getColumnByKey("description").setHeader("Description");
        grid.getGrid().getColumnByKey("remarks").setHeader("Remarks");

        grid.getCrudLayout().addToolbarComponent(menu);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        allMc = menu.addItem("All MC");
        routineMc = menu.addItem("Routine MC");
        htMc = menu.addItem("Hard Time MC");
        eoMc = menu.addItem("EO MC");

        allMc.addClickListener(e -> {
            grid.setFindAllOperation(() -> service.findAllCards(project.getArangoId()));

        });

        routineMc.addClickListener(e -> {
            grid.setFindAllOperation(() -> service.findRoutineCards(project.getArangoId()));
        });
        htMc.addClickListener(e -> {
            grid.setFindAllOperation(() -> service.findHtCards(project.getArangoId()));
        });
        eoMc.addClickListener(e -> {
            grid.setFindAllOperation(() -> service.findEoCards(project.getArangoId()));
        });
    }

}
