package uz.uat.mro.apps.views.marketing.views;

import java.util.List;

import com.github.appreciated.apexcharts.config.annotations.Label;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import uz.uat.mro.apps.model.alt.common.Maintenance;

public class MaintenancesField extends Div implements HasValue {
    private List<Maintenance> service;
    private List<Maintenance> maintenances;
    private Label label;
    private Button editButton;

    public MaintenancesField(List<Maintenance> maintenances, List<Maintenance> service) {
        this.maintenances = maintenances;
        button();
        labelField();

    }

    private void labelField() {
        this.label = new Label();
        StringBuilder sb = new StringBuilder();
        for (Maintenance maintenance : maintenances) {
            sb.append(maintenance.getIndex() + maintenance.getCode() + ",");
        }
        String s = sb.toString().replaceFirst(",(?!.*,)", "");
        label.setText(s);
    }

    private void button() {
        this.editButton = new Button(VaadinIcon.EDIT.create());
        this.editButton.addClickListener(click -> {
            Dialog div = chooser();
            div.open();
        });
    }

    private Dialog chooser() {
        Dialog div = new Dialog();
        CheckboxGroup group = new CheckboxGroup<>("Виды обслуживания");
        List<Maintenance> ms = service;
        for (Maintenance maintenance : ms) {
            Checkbox checkbox = new Checkbox(maintenance.getIndex() + maintenance.getCode());
            group.add(maintenance.getIndex() + maintenance.getCode());
            if (maintenances.contains(maintenance)) {
                checkbox.setValue(true);
            }

        }
        Button confirm = new Button("Подтвердить");
        Button cancel = new Button("Отменить");
        div.add(group, confirm, cancel);
        return div;

    }

}
