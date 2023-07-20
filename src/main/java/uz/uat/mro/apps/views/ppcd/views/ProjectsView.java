package uz.uat.mro.apps.views.ppcd.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.marketing.service.ProjectService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.utils.ProjectStatuses;
import uz.uat.mro.apps.views.ppcd.layouts.PpcdLayout;

@PageTitle(value = "Список проектов")
@Route(value = "ppcd/projects", layout = PpcdLayout.class)
public class ProjectsView extends VerticalLayout {
    private ProjectService service;
    private GridCrud<Project> grid;
    private Project project;
    private Button viewButton;
    private ComboBox<String> statuses;
    private GridListDataView<Project> listDataView;

    public ProjectsView(ProjectService service) {
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
        grid.getGrid().addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);

        CrudFormFactory<Project> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("status", "number", "date", "customer", "supplier", "aircraft", "edition",
                "startDate", "endDate", "maintenanceString");
        factory.setFieldCaptions("Статус", "Номер", "Дата", "Заказчик", "Исполнитель", "ВС номер", "Издание MPD",
                "Дата начала", "Дата окончания", "Виды работ");

        factory.setFieldProvider("customer", user -> {
            ComboBox<Organization> customers = new ComboBox<>();
            customers.setItems(service.findAllCustomers());
            customers.setItemLabelGenerator(e -> e.getShortName());
            return customers;
        });
        factory.setFieldProvider("supplier", user -> {
            ComboBox<Organization> suppliers = new ComboBox<>();
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
            UI.getCurrent().navigate(ProjectView.class);
        });
        grid.getCrudLayout().addToolbarComponent(viewButton);
        grid.getDeleteButton().setEnabled(false);
        grid.getDeleteButton().setVisible(false);

        this.listDataView = grid.getGrid().getListDataView();
        grid.getCrudLayout().addToolbarComponent(viewButton);
        statusesFilter();
        Label label = new Label("Статус");
        grid.getCrudLayout().addFilterComponents(label, statuses);

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
