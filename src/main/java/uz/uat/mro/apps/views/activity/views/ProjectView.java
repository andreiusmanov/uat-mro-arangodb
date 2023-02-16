package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Проект")
@Route(value = "project", layout = ProjectLayout.class)
public class ProjectView extends VerticalLayout {
    private FormLayout form;
    private ProjectService service;
    private Binder<Project> binder;
    private TextField number;
    private DatePicker date;

    ProjectView(ProjectService service) {
        this.service = service;

    }

    private void form() {
        this.form = new FormLayout();

    }
}
