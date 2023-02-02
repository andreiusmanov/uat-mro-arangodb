package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class CsvFileSelector extends FormLayout {
    private TextArea fileLocation;

    public CsvFileSelector() {
        this.fileLocation = new TextArea("Расположение файла");
        fileLocation.setSizeFull();
        this.setColspan(fileLocation, 2);

        HorizontalLayout l1 = new HorizontalLayout(fileLocation); // todo ,chooseButton);
        l1.setSizeFull();
        // l1.setAlignSelf(Alignment.BASELINE, chooseButton);
        add(l1);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }
}
