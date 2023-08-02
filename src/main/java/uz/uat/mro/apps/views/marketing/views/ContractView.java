package uz.uat.mro.apps.views.marketing.views;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.marketing.layouts.ContractLayout;

@PageTitle(value = "Контракт")
@Route(value = "marketing/contract", layout = ContractLayout.class)
public class ContractView extends VerticalLayout {

    private FormLayout form;
    private TextField number;
    private TextField date;
    private TextField customer;
    private TextField supplier;
    private TextField aircraft;
    private TextField startDate;
    private TextField endDate;
    private TextField maintenanceString;
    private TextField edition;
    private Project project;

    ContractView() {
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        form();
        add(form);
    }

    private void form() {
        this.form = new FormLayout();

        number = new TextField("Номер контракта");
        number.setValue(project.getNumber());
        number.setReadOnly(true);
        date = new TextField("Дата");
        date.setValue(project.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        date.setReadOnly(true);
        startDate = new TextField("Дата начала");
        startDate.setValue(project.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        startDate.setReadOnly(true);
        endDate = new TextField("Дата окончания");
        endDate.setValue(project.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        endDate.setReadOnly(true);
        aircraft = new TextField("ВС номер");
        aircraft.setValue(project.getAircraft().getRegNumber());
        aircraft.setReadOnly(true);
        customer = new TextField("Заказчик");
        customer.setValue(project.getCustomer().getShortName());
        customer.setReadOnly(true);
        supplier = new TextField("Исполнитель");
        supplier.setValue(project.getSupplier().getShortName());
        supplier.setReadOnly(true);
        maintenanceString = new TextField("Виды сервиса");
        maintenanceString.setValue(project.getMaintenanceString());
        maintenanceString.setReadOnly(true);
        edition = new TextField("MPD version");
        edition.setValue(project.getEdition().getNumber() + " " + project.getEdition().getDate());
        edition.setReadOnly(true);

        form.add(number, date, customer, supplier, aircraft, edition, startDate, endDate, maintenanceString);
    }
}
