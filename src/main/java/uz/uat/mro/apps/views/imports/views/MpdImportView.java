package uz.uat.mro.apps.views.imports.views;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.library.service.DataImportService;
import uz.uat.mro.apps.utils.ImportMpd;
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
    private ComboBox<MpdEdition> editionComboBox;

    public MpdImportView(DataImportService service) {
        this.service = service;
        combobox();
        accordion();
        button();
        add(editionComboBox, accordion, importButton);
    }

    private void combobox() {
        this.editionComboBox = new ComboBox<>();
        this.editionComboBox.setItems(service.getMpdEditions());
        this.editionComboBox.setItemLabelGenerator(
                (edition) -> edition.getNumber() + " " + edition.getDate() + " " + edition.getMessage());
        this.editionComboBox.addValueChangeListener(event -> {
            this.edition = event.getValue();
            this.accordion.setVisible(editionComboBox.getValue() != null);
            this.importButton.setEnabled(editionComboBox.getValue() != null);
        });
        editionComboBox.setLabel("Выберите издание MPD");
        editionComboBox.setPlaceholder("Выберите издание MPD");
        editionComboBox.setRequired(true);
        editionComboBox.setErrorMessage("Выберите издание MPD");
        editionComboBox.setRequiredIndicatorVisible(true);
        editionComboBox.setSizeFull();
        editionComboBox.addValueChangeListener(event -> {
            accordion.setVisible(event.getValue() != null);
            importButton.setEnabled(event.getValue() != null);
        });
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
        accordion.setVisible(editionComboBox.getValue() != null);
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

        importButton.setEnabled(editionComboBox.getValue() != null);
    }
}
