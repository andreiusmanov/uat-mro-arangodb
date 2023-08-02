package uz.uat.mro.apps.views.activity.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.service.RevisionService;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.utils.RevisionStatuses;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;
import uz.uat.mro.apps.views.ppcd.views.RevisionDownloadView;

@PageTitle(value = "Ревизии LOV MC")
@Route(value = "ppcd/revisions/lovmc", layout = ProjectLayout.class)
public class RevisionsLovView extends VerticalLayout {

    private RevisionService service;
    private Project project;
    private Revision revision;
    private GridCrud<Revision> grid;
    private Button download;

    public RevisionsLovView(RevisionService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute("project");
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Revision.class);
        this.grid.getGrid().addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        this.grid.getGrid().setItems(service.getRevisionsByProject(project.getArangoId()));
        this.grid.getGrid().setColumns("number", "date", "description", "project.number", "status");

        this.grid.getGrid().getColumnByKey("project.number").setHeader("Контракт");
        this.grid.getGrid().getColumnByKey("number").setHeader("Номер");
        this.grid.getGrid().getColumnByKey("date").setHeader("Дата");
        this.grid.getGrid().getColumnByKey("description").setHeader("Описание");
        this.grid.getGrid().getColumnByKey("status").setHeader("Статус");

        grid.getGrid().getSelectionModel().addSelectionListener(rev -> {
            boolean selected = !grid.getGrid().getSelectedItems().isEmpty();
            revision = grid.getGrid().getSelectionModel().getFirstSelectedItem().get();
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
        factory.setVisibleProperties("project.number", "number", "date", "description", "status");
        factory.setFieldCaptions("Контракт", "Номер", "Дата", "Описание", "Статус");

        factory.setFieldProvider("project.number", element -> {
            TextField t = new TextField(project.getNumber());
            t.setReadOnly(true);
            return t;
        });

        factory.setFieldProvider("status", element -> {
            ComboBox<String> c = new ComboBox<>("Статус", RevisionStatuses.statuses());
            return c;
        });

        factory.setFieldProvider("description", element -> {
            return new TextArea();
        });

        this.download = new Button(VaadinIcon.DOWNLOAD.create());
        this.download.setTooltipText("Загрузка списка TC");
        this.download.setEnabled(false);
        this.download.addClickListener(click -> {
            MyUtils.setAttribute(Keys.REVISION, revision);
            UI.getCurrent().navigate(RevisionDownloadView.class);
        });
        grid.getCrudLayout().addToolbarComponent(download);
    }




}
