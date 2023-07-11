package uz.uat.mro.apps.views.ppcd.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.opencsv.exceptions.CsvException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.ppcd.entity.ImportedCard;
import uz.uat.mro.apps.model.ppcd.service.ImportedCardsService;
import uz.uat.mro.apps.utils.ImportMC;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Загрузка списка рабочих карт")
@Route(value = "ppcd/revision/download", layout = ProjectLayout.class)
public class RevisionDownloadView extends VerticalLayout {

    private ImportedCardsService service;
    private MaintenanceCardsService service2;
    private Revision revision;
    private Project project;
    private Dialog uploadDialog;
    private GridCrud<ImportedCard> grid;
    private Button startUpload;
    private Button confirmButton;
    private Button cancelButton;

    private Button importButton;
    private Button cancel2Button;

    public RevisionDownloadView(ImportedCardsService service, MaintenanceCardsService service2) {
        this.service = service;
        this.service2 = service2;
        this.revision = (Revision) MyUtils.getAttribute(Keys.REVISION);
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);

        grid();
        dialog();
        add(new H3("Загрузка данных LOV MC"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(ImportedCard.class);
        grid.getGrid().setPageSize(20);
        grid.getGrid().setColumns("action", "sequence", "number", "revisionNumber", "taskGroup", "mhrs",
                "description", "remarks", "status");

        grid.getGrid().getColumnByKey("action").setHeader("Действие");
        grid.getGrid().getColumnByKey("sequence").setHeader("№№");
        grid.getGrid().getColumnByKey("number").setHeader("Номер TC");
        grid.getGrid().getColumnByKey("revisionNumber").setHeader("Ревизия");
        grid.getGrid().getColumnByKey("taskGroup").setHeader("Группа задач");
        grid.getGrid().getColumnByKey("mhrs").setHeader("MHRs");
        grid.getGrid().getColumnByKey("description").setHeader("Описание");
        grid.getGrid().getColumnByKey("remarks").setHeader("Пометки");
        grid.getGrid().getColumnByKey("status").setHeader("Статус");

        this.confirmButton = new Button("Подтвердить");
        this.cancelButton = new Button("Отклонить");
        this.startUpload = new Button("Загрузить");

        confirmButton.addClickListener(click -> {
            ImportMC.convertImportedCards2MaintenanceCards(service, service2, revision, project);
        });

        grid.getCrudLayout().addToolbarComponent(confirmButton);
        grid.getCrudLayout().addToolbarComponent(cancelButton);
        grid.getCrudLayout().addToolbarComponent(startUpload);

        grid.setDeleteOperation(service::delete);
        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setFindAllOperation(() -> service.findByRevision(revision));
    }

    private void dialog() {
        this.uploadDialog = new Dialog();

        this.importButton = new Button("Import");
        this.cancel2Button = new Button("Cancel");

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> {
            InputStream inputStream = buffer.getInputStream();
            try {
                ImportMC.importRevisionCards(service, inputStream, revision);
                List<ImportedCard> l = service.findByRevision(revision);
                grid.getGrid().setItems(l);
                Notification.show("Импортировано " + l.size() + " записей.", 3, Position.MIDDLE);
                uploadDialog.close();
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        });
        uploadDialog.add(upload);
        startUpload.addClickListener(e -> {
            uploadDialog.open();
        });
    }
}
