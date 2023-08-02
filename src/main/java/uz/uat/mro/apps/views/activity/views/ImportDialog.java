package uz.uat.mro.apps.views.activity.views;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.utils.ImportMC;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;

public class ImportDialog extends Dialog {
    private MaintenanceCardsService service;
    private FormLayout form;
    private TextField fileName;
    private TextField fileMpd;
    private Button load;
    private Project project;

    public ImportDialog(MaintenanceCardsService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        form();
        load();
        add(form);
    }

    private void form() {
        this.form = new FormLayout();
        this.fileName = new TextField("Файл LOV Maintenance Cards");
        this.fileMpd = new TextField("Файл MPD Task Cards");
        this.load = new Button("Загрузить");
        form.add(fileName, fileMpd, load);
    }

    private void load() {
        load.addClickListener(click -> {
            try {
                ImportMC.importMaintenanceCards(service, fileName.getValue(), project);
                ImportMC.importCards(service, fileMpd.getValue(), project);
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        });
    }
}
