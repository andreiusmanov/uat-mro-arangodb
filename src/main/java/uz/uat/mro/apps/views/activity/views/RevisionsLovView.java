package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.service.RevisionService;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;
import uz.uat.mro.apps.views.ppcd.views.RevisionDownloadView;

@PageTitle(value = "Ревизии LOV MC")
@Route(value = "ppcd/revisions/lovmc", layout = ProjectLayout.class)
public class RevisionsLovView extends VerticalLayout {

    private RevisionService service;
    private Project project;
    private Revision revision;
    private MenuBar menu;
    private MenuItem importItem;
    private MenuItem revisionItem;
    private GridCrud<Revision> grid;
    private Button download;

    public RevisionsLovView(RevisionService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute("project");

        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Revision.class);
        this.grid.getGrid().setItems(service.getRevisionsByProject(project.getArangoId()));
        this.grid.getGrid().setColumns("number", "date", "description", "project.number");
        this.grid.getGrid().getColumnByKey("project.number").setHeader("Контракт");

        grid.getGrid().getSelectionModel().addSelectionListener(rev -> {
            boolean selected = !grid.getGrid().getSelectedItems().isEmpty();
            grid.getGrid().getSelectionModel().getFirstSelectedItem();
            download.setEnabled(selected);
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.getRevisionsByProject(project.getArangoId()));
        CrudFormFactory<Revision> factory = grid.getCrudFormFactory();
        factory.setNewInstanceSupplier(() -> {
            Revision r = new Revision();
            r.setProject(project);
            return r;
        });
        factory.setVisibleProperties("project.number", "number", "date", "description");
        factory.setFieldCaptions("Контракт", "Номер", "Дата", "Описание");

        factory.setFieldProvider("description", element -> {
            return new TextArea();
        });

        this.download = new Button(VaadinIcon.DOWNLOAD.create());
        this.download.setEnabled(false);
        this.download.addClickListener(click -> {
            MyUtils.setAttribute(Keys.REVISION, revision);
            UI.getCurrent().navigate(RevisionDownloadView.class);
        });
        grid.getCrudLayout().addToolbarComponent(download);

    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        importItem = menu.addItem("Импорт", "Импорт LOV Maintenance Cards");
        revisionItem = menu.addItem("Ревизия", "Регистрация ревизии LOV Maintenance Cards");

        importItem.addClickListener(click -> {
            Notification.show("Arranging import of LOV MC");
        });

        revisionItem.addClickListener(click -> {
            Notification.show("Arranging import of revisions LOV MC");
        });
    }

}
