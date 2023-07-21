package uz.uat.mro.apps.views.imports.views;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.library.service.DataImportService;
import uz.uat.mro.apps.utils.ImportMpd;
import uz.uat.mro.apps.views.imports.layouts.ImportLayout;
import uz.uat.mro.apps.views.library.view.CsvFilePanel;

@PageTitle("Импорт данных ВС")
@Route(value = "import/aircraft-import", layout = ImportLayout.class)
public class AircraftImportView extends VerticalLayout {
    private DataImportService service;
    // private MpdEdition edition;
    private Accordion accordion;
    private CsvFilePanel zonesPanel;
    private CsvFilePanel subzonesPanel;
    private CsvFilePanel accessesPanel;
    private CsvFilePanel accessesSynthPanel;
    private Button importButton;
    private ComboBox<MajorModel> majorModelComboBox;
    private MajorModel majorModel;

    public AircraftImportView(DataImportService service) {
        this.service = service;
        combobox();
        accordion();
        button();
        add(majorModelComboBox, accordion, importButton);
    }

    private void combobox() {
        this.majorModelComboBox = new ComboBox<>();
        this.majorModelComboBox.setItems(service.getMajorModels());
        this.majorModelComboBox.setItemLabelGenerator(MajorModel::getName);
        this.majorModelComboBox.addValueChangeListener(event -> {
            this.majorModel = event.getValue();
        });
majorModelComboBox.setLabel("Выберите модель ВС");
    }

    private void accordion() {
        this.accordion = new Accordion();
        this.zonesPanel = new CsvFilePanel("Импорт зон ВС");
        this.subzonesPanel = new CsvFilePanel("Импорт субзон ВС");
        this.accessesPanel = new CsvFilePanel("Импорт доступов ВС");
        this.accessesSynthPanel = new CsvFilePanel("Импорт синтетических доступов ВС");
        accordion.add(zonesPanel);
        accordion.add(subzonesPanel);
        accordion.add(accessesPanel);
        accordion.add(accessesSynthPanel);
        accordion.setSizeFull();
    }

    private void button() {
        this.importButton = new Button("Загрузить");
        importButton.addClickListener(clickEvent -> {
            try {
                ImportMpd.importBoeingZones(service, zonesPanel.getFileName(), majorModel);
                ImportMpd.importBoeingSubzones(service, subzonesPanel.getFileName(), majorModel);
                ImportMpd.importBoeingAccesses(service, accessesPanel.getFileName(), majorModel);
                ImportMpd.importBoeingAccessesSynth(service, accessesSynthPanel.getFileName(), majorModel);
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }
        });
    }
}
