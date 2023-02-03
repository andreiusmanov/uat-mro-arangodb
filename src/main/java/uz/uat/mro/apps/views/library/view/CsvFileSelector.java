package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class CsvFileSelector extends FormLayout {
    private TextArea fileLocation;

    public CsvFileSelector() {
        this.fileLocation = new TextArea("Расположение файла");
        fileLocation.setSizeFull();
        setColspan(fileLocation, 3);
        add(fileLocation);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }
}
