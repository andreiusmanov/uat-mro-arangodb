package uz.uat.mro.apps.views.activity.views;

import java.time.LocalDate;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.marketing.service.ProjectService;

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
        settings();
        form();
        add(form);
    }

    private void settings() {
        this.setCloseOnEsc(true);
    }

    private void form() {
        this.form = new FormLayout();
        this.number = new TextField("Номер контракта");
        this.date = new DatePicker("Дата", LocalDate.now());
        this.customer = new ComboBox<>("Заказчик");
        customer.setItems(service.findAllCustomers());
        customer.setItemLabelGenerator(firm -> firm.getShortName());
        this.supplier = new ComboBox<>("Исполнитель");
        supplier.setItems(service.findAllSuppliers());
        supplier.setItemLabelGenerator(firm -> firm.getShortName());
        this.startDate = new DatePicker("Дана начала", LocalDate.now());
        this.endDate = new DatePicker("Дата окончания", LocalDate.now());
        this.aircraft = new ComboBox<>("ВС номер");
        aircraft.setItems(service.findAllAircrafts());
        aircraft.setItemLabelGenerator(ac -> ac.getRegNumber());

        this.binder = new Binder<>(Project.class);
        binder.forField(number).bind(e -> e.getNumber(), (e, num) -> e.setNumber(num));
        binder.forField(date).bind(e -> e.getDate(), (e, num) -> e.setDate(num));
        binder.forField(customer).bind(e -> e.getCustomer(), (e, num) -> e.setCustomer(num));
        binder.forField(supplier).bind(e -> e.getSupplier(), (e, num) -> e.setSupplier(num));
        binder.forField(startDate).bind(e -> e.getStartDate(), (e, num) -> e.setStartDate(num));
        binder.forField(endDate).bind(e -> e.getEndDate(), (e, num) -> e.setEndDate(num));
        binder.forField(aircraft).bind(e -> e.getAircraft(), (e, num) -> e.setAircraft(num));
    }

}
