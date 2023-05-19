package uz.uat.mro.apps.views.ppcd.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.ppcd.entity.ImportedCards;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Загрузка списка рабочих карт")
@Route(value = "ppcd/revision/download", layout = ProjectLayout.class)
public class RevisionDownloadView extends VerticalLayout {

    private MaintenanceCardsService service;
    private TextField url;
    private Button chooser;
    private Dialog uploadDialog;
    private GridCrud<ImportedCards> grid;
    private Button confirmButton;
    private Button cancelButton;

    public RevisionDownloadView(MaintenanceCardsService service) {
        this.service = service;

        grid();
        add(new H3("Загрузка данных LOV MC"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(ImportedCards.class);
        grid.getGrid().setPageSize(20);

        this.confirmButton = new Button("Подтвердить");
        this.cancelButton = new Button("Отклонить");

        grid.getCrudLayout().addToolbarComponent(confirmButton);
        grid.getCrudLayout().addToolbarComponent(cancelButton);
    }

}
