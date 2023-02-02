package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.library.service.MpdImportService;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle("Импорт данных MPD")
@Route(value = "mpd/import", layout = MpdLayout.class)
public class MpdImportView extends VerticalLayout {
    private MpdImportService service;
    private Accordion accordion;
    private AccordionPanel zonesPanel;
    private AccordionPanel subzonesPanel;
    private AccordionPanel accessesPanel;
    private AccordionPanel mhsPanel;
    private AccordionPanel itemsPanel;
    private AccordionPanel taskcardsPanel;
    private Button importButton;

    public MpdImportView(MpdImportService service) {
        this.service = service;
        accordion();
        button();
        add(accordion);
    }

    private void accordion() {
        this.accordion = new Accordion();
        this.zonesPanel = new AccordionPanel("Импорт зон ВС");
        zonesPanel.addContent(new H3("Заполните таблицу зон ВС на странице 'Зоны ВС'"));
        this.subzonesPanel = new AccordionPanel("Импорт субзон ВС");
        this.subzonesPanel.addContent(new FileSelector());
        this.accessesPanel = new AccordionPanel("Импорт доступов ВС");
        this.accessesPanel.addContent(new FileSelector());
        this.mhsPanel = new AccordionPanel("Импорт данных Man Hours");
        this.mhsPanel.addContent(new FileSelector());
        this.itemsPanel = new AccordionPanel("Импорт MPD Item");
        itemsPanel.addContent(new ExcelFileSelector());
        this.taskcardsPanel = new AccordionPanel("Импорт MPD Taskcards");
        taskcardsPanel.addContent(new ExcelFileSelector());
        accordion.add(zonesPanel);
        accordion.add(subzonesPanel);
        accordion.add(accessesPanel);
        accordion.add(mhsPanel);
        accordion.add(itemsPanel);
        accordion.add(taskcardsPanel);
    }

    private void button(){
        this.importButton = new Button();
        importButton.addClickListener(clickEvent ->{

        });
    }
}
