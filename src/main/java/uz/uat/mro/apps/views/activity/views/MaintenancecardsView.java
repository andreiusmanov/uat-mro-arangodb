package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;
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
    private MaintenanceCard card;

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
        // grid.getGrid().setSelectionMode(SelectionMode.MULTI);

        grid.getGrid().setColumns("sequence", "number", "revision.number", "mhrs", "taskGroup.name",
                "taskcardString", "description", "remarks", "status");
        grid.getGrid().getColumnByKey("sequence").setHeader("№№");
        grid.getGrid().getColumnByKey("number").setHeader("Number");
        grid.getGrid().getColumnByKey("revision.number").setHeader("Ревизия");
        grid.getGrid().getColumnByKey("mhrs").setHeader("MHs");
        grid.getGrid().getColumnByKey("taskGroup.name").setHeader("Тип задания");
        grid.getGrid().getColumnByKey("taskcardString").setHeader("MPD TC");
        grid.getGrid().getColumnByKey("description").setHeader("Описание");
        grid.getGrid().getColumnByKey("remarks").setHeader("Пометки");
        grid.getGrid().getColumnByKey("status").setHeader("Статус");

        downloadButton = new Button(VaadinIcon.DOWNLOAD.create());
        downloadButton.addClickListener(click -> {
            ImportDialog dialog = new ImportDialog(service);
            dialog.open();
            dialog.setCloseOnEsc(true);
        });
        grid.getCrudLayout().addToolbarComponent(downloadButton);
        grid.getCrudLayout().addToolbarComponent(menu);

        this.card = grid.getGrid().getSelectionModel().getFirstSelectedItem().orElse(null);

         grid.getCrudFormFactory().buildNewForm(CrudOperation.READ, card,
         isAttached(), null, null)

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
