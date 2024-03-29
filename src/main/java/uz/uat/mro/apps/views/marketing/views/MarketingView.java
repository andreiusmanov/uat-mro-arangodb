package uz.uat.mro.apps.views.marketing.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.marketing.service.ProjectService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.utils.ProjectStatuses;
import uz.uat.mro.apps.views.marketing.layouts.MarketingLayout;

@PageTitle(value = "Маркетинг")
@Route(value = "marketing", layout = MarketingLayout.class)
public class MarketingView extends VerticalLayout {

    private ProjectService service;
    private GridCrud<Project> grid;
    private Project project;
    private Button viewButton;
    private ComboBox<String> statuses;
    private GridListDataView<Project> listDataView;

    public MarketingView(ProjectService service) {
        this.service = service;
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Project.class);
        grid.getGrid().addSelectionListener(select -> {
            project = grid.getGrid().getSelectionModel().getFirstSelectedItem().orElse(null);
            viewButton.setEnabled(!grid.getGrid().getSelectedItems().isEmpty());
        });

        grid.getGrid().setColumns("status", "number", "date", "customer.shortName", "supplier.shortName",
                "aircraft.regNumber",
                "maintenanceString");
        grid.getGrid().getColumnByKey("status").setHeader("Статус");
        grid.getGrid().getColumnByKey("number").setHeader("Номер");
        grid.getGrid().getColumnByKey("date").setHeader("Дата");
        grid.getGrid().getColumnByKey("customer.shortName").setHeader("Заказчик");
        grid.getGrid().getColumnByKey("supplier.shortName").setHeader("Исполнитель");
        grid.getGrid().getColumnByKey("aircraft.regNumber").setHeader("ВС номер");
        grid.getGrid().getColumnByKey("maintenanceString").setHeader("Тип ТОиР");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);

        CrudFormFactory<Project> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("status", "number", "date", "customer", "supplier", "aircraft", "edition",
                "startDate", "endDate", "maintenanceString");
        factory.setFieldCaptions("Статус", "Номер", "Дата", "Заказчик", "Исполнитель", "ВС номер", "Издание MPD",
                "Дата начала", "Дата окончания", "Виды работ");

        factory.setFieldProvider("status", status -> {
            ComboBox<String> statuses = new ComboBox<>();
            statuses.setItems(ProjectStatuses.projectStatuses());
            statuses.setItemLabelGenerator(e -> e);
            return statuses;
        });
        factory.setFieldProvider("customer", user -> {
            ComboBox<Firm> customers = new ComboBox<>();
            customers.setItems(service.findAllCustomers());
            customers.setItemLabelGenerator(e -> e.getShortName());
            return customers;
        });
        factory.setFieldProvider("supplier", user -> {
            ComboBox<Firm> suppliers = new ComboBox<>();
            suppliers.setItems(service.findAllSuppliers());
            suppliers.setItemLabelGenerator(e -> e.getShortName());
            return suppliers;
        });

        factory.setFieldProvider("aircraft", user -> {
            ComboBox<Aircraft> aircrafts = new ComboBox<>();
            aircrafts.setItems(service.findAllAircrafts());
            aircrafts.setItemLabelGenerator(e -> e.getRegNumber());
            return aircrafts;
        });

        factory.setFieldProvider("edition", user -> {
            ComboBox<MpdEdition> editions = new ComboBox<>();
            editions.setItems(service.findAllEditions());
            editions.setItemLabelGenerator(e -> e.getNumber() + " " + e.getDate());
            return editions;
        });

        viewButton = new Button(VaadinIcon.EYE.create());
        viewButton.setEnabled(false);
        viewButton.addClickListener(click -> {
            MyUtils.setAttribute(Keys.PROJECT, project);
            UI.getCurrent().navigate(ContractView.class);
        });
        this.listDataView = grid.getGrid().getListDataView();
        grid.getCrudLayout().addToolbarComponent(viewButton);
        statusesFilter();
        grid.getCrudLayout().addFilterComponent(statuses);
    }

    private void statusesFilter() {
        statuses = new ComboBox<>();
        statuses.setItems(ProjectStatuses.projectStatuses());
        statuses.setValue(statuses.getListDataView().getItem(1));
        statuses.addValueChangeListener(change -> {
            listDataView.setFilter(project -> project.getStatus().equals(change.getValue()));
        });
    }

}
