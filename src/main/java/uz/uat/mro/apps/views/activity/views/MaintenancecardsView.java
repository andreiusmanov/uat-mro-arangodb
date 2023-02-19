package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "List of valid Maintenance Cards")
@Route(value = "activity/maintenancecards", layout = ProjectLayout.class)
public class MaintenancecardsView extends VerticalLayout {

    private ProjectService service;
    private Project project;
    private GridCrud<MaintenanceCard> grid;
    private MenuBar menu;

    public MaintenancecardsView(ProjectService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(MaintenanceCard.class);
        grid.setAddOperation(service::saveCard);
        grid.setUpdateOperation(service::saveCard);
        grid.setDeleteOperation(service::deleteCard);
        grid.setFindAllOperation(() -> service.findAllCards(project.getArangoId()));
    }

    private void menu() {
    }

}
