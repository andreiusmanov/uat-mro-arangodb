package uz.uat.mro.apps.views.marketing.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.project.ProjectService;
import uz.uat.mro.apps.utils.MyUtils;

@PageTitle(value = "Новый Контракт")
@Route(value = "marketing/new-contract")
public class NewContractView extends VerticalLayout {
    private ProjectService service;
    private FormLayout form;
    private TextField number;
    private DatePicker date;
    private ComboBox<Organization> customer;
    private ComboBox<Organization> supplier;
    private ComboBox<Aircraft> aircraft;
    private DatePicker startDate;
    private DatePicker endDate;
    private TextField maintenances;
    private ComboBox<MpdEdition> edition;
    private Project project;
    private Button saveButton;
    private Button cancelButton;

    public NewContractView(ProjectService service) {
        this.service = service;
        form();
        add(form);
        buttons();
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        this.cancelButton = new Button("Отменить");
        this.saveButton.addClickListener(e -> {
            project = new Project();
            project.setNumber(number.getValue());
            project.setDate(date.getValue());
            project.setCustomer(customer.getValue());
            project.setSupplier(supplier.getValue());
            project.setAircraft(aircraft.getValue());
            project.setStartDate(startDate.getValue());
            project.setEndDate(endDate.getValue());
            project.setEdition(edition.getValue());
            Project savedProject = service.save(project);
            MyUtils.showSuccessNotification("Контракт сохранен");
            MyUtils.setAttribute("project", savedProject);
            this.getUI().ifPresent(ui -> ui.navigate("marketing/contracts"));
        });

        this.cancelButton.addClickListener(e -> {
            MyUtils.showCancelNotification("Отменено");
            this.getUI().ifPresent(ui -> ui.navigate("marketing/contracts"));
        });
    }

    private void form() {
        this.form = new FormLayout();
        number = new TextField("Номер контракта");
        number.setValue(project.getNumber());
        number.setReadOnly(true);
        date = new DatePicker("Дата");
        startDate = new DatePicker("Дата начала");
        endDate = new DatePicker("Дата окончания");
        aircraft = new ComboBox<>("ВС номер");
        aircraft.setItems(service.findAllAircrafts());
        aircraft.setItemLabelGenerator(Aircraft::getRegNumber);
        customer = new ComboBox<>("Заказчик");
        customer.setItems(service.findAllCustomers());
        customer.setItemLabelGenerator(Organization::getShortName);

        supplier = new ComboBox<>("Заказчик");
        supplier.setItems(service.findAllCustomers());
        supplier.setItemLabelGenerator(Organization::getShortName);

        maintenances = new TextField("Виды сервиса");
        maintenances.setValue(service.maintenance2String(project.getMaintenance()));
        maintenances.setReadOnly(true);
        edition = new ComboBox<>("MPD version");
        edition.setItems(service.findAllEditions());
        edition.setItemLabelGenerator(edition -> edition.getNumber() + " " + edition.getDate());

        form.add(number, date, customer, supplier, aircraft, edition, startDate, endDate, maintenances);
    }

}
