package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class XlsFilePanel extends AccordionPanel {
    private FormLayout layout;
    private TextArea fileLocation;
    private TextField sheetName;

    public XlsFilePanel(String summary) {
        this.layout = new FormLayout();
        this.fileLocation = new TextArea("Расположение файла");
        this.sheetName = new TextField("Наименование страницы");
        fileLocation.setSizeFull();
        layout.add(fileLocation, sheetName);
        layout.setColspan(fileLocation, 2);
        layout.setColspan(sheetName, 2);
        setSummaryText(summary);
        setContent(layout);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }

    public String getSheetName() {
        return sheetName.getValue();
    }
}
