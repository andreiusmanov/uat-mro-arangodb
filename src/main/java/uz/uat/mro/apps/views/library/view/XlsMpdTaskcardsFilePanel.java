package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.apps.utils.MpdKeys;

public class XlsMpdTaskcardsFilePanel extends AccordionPanel {
    private FormLayout layout;
    private TextArea fileLocation;
    private TextField taskcardsSheet;

    public XlsMpdTaskcardsFilePanel(String summary) {
        this.layout = new FormLayout();
        this.fileLocation = new TextArea("Расположение файла");
        this.fileLocation.setValue("/home/andreyu/Documents/B-757/data/mpdsup.xls");
        this.taskcardsSheet = new TextField("MPD Рабочие карты");
        this.taskcardsSheet.setValue("TASKS SORTED BY TASK CARD NUMBE");
        fileLocation.setSizeFull();
        layout.add(fileLocation, taskcardsSheet);
        layout.setColspan(fileLocation, 2);
        setSummaryText(summary);
        setContent(layout);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }

    public String getSheet(String name) {
        return MpdKeys.TASKCARDS;
    }

    public String getSheet() {
        return taskcardsSheet.getValue();
    }
}
