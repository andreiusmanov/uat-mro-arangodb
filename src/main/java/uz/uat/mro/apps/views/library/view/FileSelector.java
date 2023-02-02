package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;

public class FileSelector extends FormLayout {
    private TextArea fileLocation;
    private NumberField startPage;
    private NumberField endPage;

    public FileSelector() {
        this.fileLocation = new TextArea("Расположение файла");
        fileLocation.setSizeFull();
        // this.chooseButton = new Button(VaadinIcon.SEARCH.create(), e -> {
        // Dialog d = new Dialog();
        // FileTree tree = new FileTree("$user/", false, e1 -> {
        // fileLocation.setValue(e1.getAbsolutePath());
        // d.close();
        // });
        // d.add(tree);
        // d.setCloseOnEsc(true);
        // d.open();
        // });
        this.startPage = new NumberField();
        this.endPage = new NumberField();
        startPage.setLabel("От страницы ...");
        endPage.setLabel("До страницы ...");
        startPage.setValue(0.00);
        endPage.setValue(0.00);
        this.setColspan(fileLocation, 2);

        HorizontalLayout l1 = new HorizontalLayout(fileLocation); // todo ,chooseButton);
        l1.setSizeFull();
        // l1.setAlignSelf(Alignment.BASELINE, chooseButton);
        HorizontalLayout l2 = new HorizontalLayout(startPage, endPage);
        add(l1, l2);
    }

    public String getFileName() {
        return fileLocation.getValue();
    }

    public Integer getStartPage() {
        return this.startPage.getValue().intValue();
    }

    public Integer getEndPage() {
        return this.endPage.getValue().intValue();
    }

}
