package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.model.marketing.service.ProjectService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle(value = "Коэффицент МRО")
@Route(value = "ppcd/coefficent", layout = PlanningLayout.class)
public class MroCoefficentView extends VerticalLayout {

    private ProjectService service;
    private Project project;
    private FormLayout form;
    private TextField projectField;
    private TextField aircraftField;
    private NumberField coefficentField;

    private Button editButton;
    private Button saveButton;

    public MroCoefficentView(ProjectService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute("project");
        form();
        add(form);
    }

    private void form() {
        this.form = new FormLayout();
        this.projectField = new TextField("Контракт");
        this.projectField.setValue(project.getNumber());
        this.projectField.setReadOnly(true);

        this.aircraftField = new TextField("В/С");
        this.aircraftField.setValue(project.getAircraft().getRegNumber());
        this.aircraftField.setReadOnly(true);

        this.coefficentField = new NumberField("Коэффицент");
        this.coefficentField.setValue(project.getCoefficent());
        this.coefficentField.setReadOnly(true);

        this.saveButton = new Button("Сохранить");
        this.saveButton.setEnabled(false);
        this.saveButton.addClickListener(click -> {
            project.setCoefficent(coefficentField.getValue());
            service.save(project);
            saveButton.setEnabled(false);
            editButton.setEnabled(true);
            coefficentField.setReadOnly(true);
        });

        this.editButton = new Button(VaadinIcon.EDIT.create());
        editButton.addClickListener(click -> {
            coefficentField.setReadOnly(false);
            saveButton.setEnabled(true);
            editButton.setEnabled(false);
        });

        Span span = new Span(coefficentField, editButton, saveButton);
        coefficentField.setWidthFull();
        form.add(projectField, aircraftField, span);
    }

}
