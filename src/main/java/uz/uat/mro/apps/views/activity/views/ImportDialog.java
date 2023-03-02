package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ImportDialog extends Dialog {
    private FormLayout form;
    private TextField fileName;
    private Button load;

    public ImportDialog() {

    }

    private void form() {
        this.form = new FormLayout();
        this.fileName = new TextField("Файл");
        this.load = new Button("Загрузить");

    }
}
