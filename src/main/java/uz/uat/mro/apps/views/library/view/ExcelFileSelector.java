package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;

public class ExcelFileSelector extends FormLayout {
    private TextArea fileLocation;
    private TextArea sheetName;

    public ExcelFileSelector() {
        this.fileLocation = new TextArea("Расположение файла excel");
        fileLocation.setSizeFull();
        this.sheetName = new TextArea("Наименование страницы");
        sheetName.setSizeFull();

        HorizontalLayout l1 = new HorizontalLayout(fileLocation); // todo ,chooseButton);
        l1.setSizeFull();
        HorizontalLayout l2 = new HorizontalLayout(sheetName);
        add(l1, l2);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }

    public String getSheetName() {
        return sheetName.getValue();
    }

}
