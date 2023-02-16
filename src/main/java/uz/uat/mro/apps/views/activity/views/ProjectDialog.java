package uz.uat.mro.apps.views.activity.views;

import java.time.LocalDate;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.common.entity.Firm;

public class ProjectDialog extends Dialog {
    private ProjectService service;
    private TextField number;
    private DatePicker date;
    private ComboBox<Firm> customer;
    private ComboBox<Firm> supplier;
    private DatePicker startDate;
    private DatePicker endDate;
    private ComboBox<Aircraft> aircraft;
    private Binder<Project> binder;
    private FormLayout form;

    public ProjectDialog(ProjectService service) {
        super();
        this.service = service;
        form();
    }

    private void form() {
        this.form = new FormLayout();
        this.number = new TextField("getAriaLabelString()");
        this.date = new DatePicker(LocalDate.now());
        this.customer = new ComboBox<>("getAriaLabelString()");
        customer.setItems(service.findAllCustomers());
        customer.setItemLabelGenerator(firm -> firm.getShortName());
        this.supplier = new ComboBox<>("getAriaLabelString()");
        supplier.setItems(service.findAllSuppliers());
        supplier.setItemLabelGenerator(firm -> firm.getShortName());
        this.startDate = new DatePicker(LocalDate.now());
        this.endDate = new DatePicker(LocalDate.now());
        this.aircraft = new ComboBox<>("getAriaLabelString()");
        aircraft.setItems(service.findAllAircrafts());
        aircraft.setItemLabelGenerator(ac -> ac.getRegNumber());

        this.binder = new Binder<>(Project.class);
        binder.forField("number").bind(e -> e.getNumber(), (num, e) -> e.setNumber(num));
    }

}
