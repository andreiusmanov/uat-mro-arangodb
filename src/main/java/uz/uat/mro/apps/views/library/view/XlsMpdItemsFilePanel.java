package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.apps.utils.MpdKeys;

public class XlsMpdItemsFilePanel extends AccordionPanel {
    private FormLayout layout;
    private TextArea fileLocation;
    private TextField systemSheet;
    private TextField structuralSheet;
    private TextField zonalSheet;

    public XlsMpdItemsFilePanel(String summary) {
        this.layout = new FormLayout();
        this.fileLocation = new TextArea("Расположение файла");
        this.fileLocation.setValue("/home/andreyu/Documents/B-757/data/mpdsup.xls");
        this.systemSheet = new TextField("Системное обслуживание");
        this.systemSheet.setValue("SYSTEMS AND POWERPLANT MAINTENA");
        this.structuralSheet = new TextField("Структурное обслуживание");
        this.structuralSheet.setValue("STRUCTURAL MAINTENANCE REQUIREM");
        this.zonalSheet = new TextField("Зональное обслуживание");
        this.zonalSheet.setValue("ZONAL INSPECTION PROGRAM");
        fileLocation.setSizeFull();
        layout.add(fileLocation, systemSheet, structuralSheet, zonalSheet);
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
            default:
                return null;
        }
    }

    public String[] getSheets() {
        return new String[] { systemSheet.getValue(), structuralSheet.getValue(), zonalSheet.getValue() };
    }

}
