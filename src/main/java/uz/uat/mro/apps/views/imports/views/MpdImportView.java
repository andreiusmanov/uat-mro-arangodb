package uz.uat.mro.apps.views.imports.views;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.library.service.DataImportService;
import uz.uat.mro.apps.utils.ImportMpd;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.imports.layouts.ImportLayout;
import uz.uat.mro.apps.views.library.view.CsvFilePanel;
import uz.uat.mro.apps.views.library.view.XlsMpdItemsFilePanel;
import uz.uat.mro.apps.views.library.view.XlsMpdTaskcardsFilePanel;

@PageTitle("Импорт данных MPD")
@Route(value = "import/import-mpd", layout = ImportLayout.class)
public class MpdImportView extends VerticalLayout {
    private DataImportService service;
    private MpdEdition edition;
    private Accordion accordion;
    private CsvFilePanel mhsPanel;
    private XlsMpdItemsFilePanel itemsPanel;
    private XlsMpdTaskcardsFilePanel taskcardsPanel;
    private Button importButton;
    private TextField editionBox;

    public MpdImportView(DataImportService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute("mpd-edition");
        textField();
        accordion();
        button();
        add(editionBox, accordion, importButton);
    }

    private void textField() {
        this.editionBox = new TextField();
        this.editionBox.setValue(edition.getNumber() + " " + edition.getDate() + " " + edition.getMessage());
        this.editionBox.setReadOnly(true);
        this.editionBox.setLabel("Издание MPD");
        this.editionBox.setRequiredIndicatorVisible(true);        
            this.editionBox.setSizeFull();
    }

    private void accordion() {
        this.accordion = new Accordion();
        this.mhsPanel = new CsvFilePanel("Импорт данных Man Hours");
        this.itemsPanel = new XlsMpdItemsFilePanel("Импорт MPD Item");
        this.taskcardsPanel = new XlsMpdTaskcardsFilePanel("Импорт MPD Taskcards");
        accordion.add(itemsPanel);
        accordion.add(taskcardsPanel);
        accordion.add(mhsPanel);
        accordion.setSizeFull();
        accordion.setVisible(!editionBox.getValue().isBlank());
        }

    private void button() {
        this.importButton = new Button("Загрузить");
        importButton.addClickListener(clickEvent -> {
            try {
                ImportMpd.importMpdItems(service, itemsPanel.getFileName(), itemsPanel.getSheets(), edition);
                ImportMpd.importMpdTaskcards(service, taskcardsPanel.getFileName(), taskcardsPanel.getSheet(), edition);
                ImportMpd.importBoeingMhs(service, mhsPanel.getFileName(), edition);
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        });
        importButton.setEnabled(!editionBox.getValue().isBlank());
    }
}
