package uz.uat.mro.apps.views.marketing.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.common.Maintenance;
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
    @PropertyId("number")
    private TextField number;
    @PropertyId("date")
    private DatePicker date;
    @PropertyId("customer")
    private ComboBox<Organization> customer;
    @PropertyId("supplier")
    private ComboBox<Organization> supplier;
    @PropertyId("aircraft")
    private ComboBox<Aircraft> aircraft;
    @PropertyId("startDate")
    private DatePicker startDate;
    @PropertyId("endDate")
    private DatePicker endDate;
    @PropertyId("maintenance")
    private MultiSelectComboBox<Maintenance> maintenances;
    @PropertyId("edition")
    private ComboBox<MpdEdition> edition;
    private Project project;
    private HorizontalLayout buttons;
    private Button saveButton;
    private Button cancelButton;
    private Binder<Project> binder;

    public NewContractView(ProjectService service) {
        this.service = service;

        form();
        add(form);
        buttons();
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        this.cancelButton = new Button("Отменить");
        buttons = new HorizontalLayout();
        buttons.add(saveButton, cancelButton);
        this.saveButton.addClickListener(e -> {
            boolean writeBeanIfValid = binder.writeBeanIfValid(project);
            if (!writeBeanIfValid) {
                MyUtils.showErrorNotification("Проверьте введенные данные");
            } else {
                Project savedProject = service.save(binder.getBean());
                MyUtils.showSuccessNotification("Контракт сохранен");
                MyUtils.setAttribute("project", savedProject);
                this.getUI().ifPresent(ui -> ui.navigate("marketing/contracts"));
            }
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

        maintenances = new MultiSelectComboBox<>("Виды сервиса");
        maintenances.setItems(service.findAllMaintenances());
        maintenances.setValue(project.getMaintenance());
        maintenances.setItemLabelGenerator(maintenance -> maintenance.getIndex() + " " + maintenance.getCode());

        edition = new ComboBox<>("MPD version");
        edition.setItems(service.findAllEditions());
        edition.setItemLabelGenerator(edition -> edition.getNumber() + " " + edition.getDate());
        form.add(number, date, customer, supplier, aircraft, edition, startDate, endDate, maintenances);

        binder = new Binder<>(Project.class, true);
        binder.setBean(project);
        binder.bindInstanceFields(this);
    }

}
