package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.docs.MaintenanceCard;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "LOV MC")
@Route(value = "activity/maintenancecards", layout = ProjectLayout.class)
public class MaintenancecardsView extends VerticalLayout {

    private MaintenanceCardsService service;
    private Project project;
    private GridCrud<MaintenanceCard> grid;
    private Button downloadButton;
    private MenuBar menu;

    private MenuItem allMc;
    private MenuItem routineMc;
    private MenuItem htMc;
    private MenuItem eoMc;

    public MaintenancecardsView(MaintenanceCardsService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        menu();
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(MaintenanceCard.class);
        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAllByProject(project.getArangoId()));
        grid.getGrid().setColumns("number", "mpReference", "taskcardString", "description", "remarks");
        grid.getGrid().getColumnByKey("number").setHeader("Number");
        grid.getGrid().getColumnByKey("mpReference").setHeader("MP Reference");
        grid.getGrid().getColumnByKey("taskcardString").setHeader("MPD Taskcard");
        grid.getGrid().getColumnByKey("description").setHeader("Description");
        grid.getGrid().getColumnByKey("remarks").setHeader("Remarks");

        downloadButton = new Button(VaadinIcon.DOWNLOAD.create());
        downloadButton.addClickListener(click -> {
            ImportDialog dialog = new ImportDialog(service);
            dialog.open();
            dialog.setCloseOnEsc(true);
        });
        grid.getCrudLayout().addToolbarComponent(downloadButton);
        grid.getCrudLayout().addToolbarComponent(menu);

    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        allMc = menu.addItem("All MC");
        routineMc = menu.addItem("Routine");
        htMc = menu.addItem("Hard Time");
        eoMc = menu.addItem("EO");
        allMc.addClickListener(e -> {
            grid.setFindAllOperation(() -> service.findAllByProject(project.getArangoId()));
        });

        routineMc.addClickListener(click -> grid.getGrid().getListDataView().setFilter(e -> {
            return e.getTaskGroup().getId().equals("routine");
        }));

        htMc.addClickListener(click -> grid.getGrid().getListDataView().setFilter(e -> {
            return e.getTaskGroup().getId().equals("ht");
        }));

        eoMc.addClickListener(click -> grid.getGrid().getListDataView().setFilter(e -> {
            return e.getTaskGroup().getId().equals("eo");
        }));
    }

}
