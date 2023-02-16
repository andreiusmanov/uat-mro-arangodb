package uz.uat.mro.apps.views.library.view;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.service.DataImportService;
import uz.uat.mro.apps.utils.ImportMpd;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle("Импорт данных MPD")
@Route(value = "mpd/import", layout = MpdLayout.class)
public class MpdImportView extends VerticalLayout {
    private DataImportService service;
    private MpdEdition edition;
    private Accordion accordion;
    private CsvFilePanel zonesPanel;
    private CsvFilePanel subzonesPanel;
    private CsvFilePanel accessesPanel;
    private CsvFilePanel accessesSynthPanel;
    private CsvFilePanel mhsPanel;
    private XlsMpdItemsFilePanel itemsPanel;
    private XlsMpdTaskcardsFilePanel taskcardsPanel;
    private Button importButton;

    public MpdImportView(DataImportService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        accordion();
        button();
        add(accordion, importButton);
    }

    private void accordion() {
        this.accordion = new Accordion();
        this.zonesPanel = new CsvFilePanel("Импорт зон ВС");
        this.subzonesPanel = new CsvFilePanel("Импорт субзон ВС");
        this.accessesPanel = new CsvFilePanel("Импорт доступов ВС");
        this.accessesSynthPanel = new CsvFilePanel("Импорт синтетических доступов ВС");
        this.mhsPanel = new CsvFilePanel("Импорт данных Man Hours");
        this.itemsPanel = new XlsMpdItemsFilePanel("Импорт MPD Item");
        this.taskcardsPanel = new XlsMpdTaskcardsFilePanel("Импорт MPD Taskcards");
        accordion.add(zonesPanel);
        accordion.add(subzonesPanel);
        accordion.add(accessesPanel);
        accordion.add(accessesSynthPanel);
        accordion.add(itemsPanel);
        accordion.add(taskcardsPanel);
        accordion.add(mhsPanel);
        accordion.setSizeFull();
    }

    private void button() {
        this.importButton = new Button("Загрузить");
        importButton.addClickListener(clickEvent -> {
            try {
                ImportMpd.importBoeingZones(service, zonesPanel.getFileName(), edition);
                ImportMpd.importBoeingSubzones(service, subzonesPanel.getFileName(), edition);
                ImportMpd.importBoeingAccesses(service, accessesPanel.getFileName(), edition);
                ImportMpd.importBoeingAccessesSynth(service, accessesSynthPanel.getFileName(), edition);
                ImportMpd.importMpdItems(service, itemsPanel.getFileName(), itemsPanel.getSheets(), edition);
                ImportMpd.importMpdTaskcards(service, taskcardsPanel.getFileName(), taskcardsPanel.getSheet(), edition);
                ImportMpd.importBoeingMhs(service, mhsPanel.getFileName(), edition);
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
