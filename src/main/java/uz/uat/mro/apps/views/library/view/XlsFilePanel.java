package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.apps.utils.MpdKeys;

public class XlsFilePanel extends AccordionPanel {
    private FormLayout layout;
    private TextArea fileLocation;
    private TextField systemSheet;
    private TextField structuralSheet;
    private TextField zonalSheet;
    private TextField taskcardsSheet;

    public XlsFilePanel(String summary) {
        this.layout = new FormLayout();
        this.fileLocation = new TextArea("Расположение файла");
        this.systemSheet = new TextField("Системное обслуживание");
        this.structuralSheet = new TextField("Структурное обслуживание");
        this.zonalSheet = new TextField("Зональное обслуживание");
        this.taskcardsSheet = new TextField("Таск карты");
        fileLocation.setSizeFull();
        layout.add(fileLocation, systemSheet, structuralSheet, zonalSheet, taskcardsSheet);
        layout.setColspan(fileLocation, 2);
        setSummaryText(summary);
        setContent(layout);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }

    public String getSheet(String name) {
        switch (name) {
            case MpdKeys.SYSTEM_ITEMS: {
                return systemSheet.getValue();
            }
            case MpdKeys.STRUCTURAL_ITEMS: {
                return structuralSheet.getValue();
            }
            case MpdKeys.ZONAL_ITEMS: {
                return zonalSheet.getValue();
            }
            case MpdKeys.TASKCARDS: {
                return taskcardsSheet.getValue();
            }
            default:
                return null;
        }

    }
}
