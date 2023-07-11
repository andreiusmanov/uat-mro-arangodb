package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle("Maintenance Plan")
@Route(value = "activity/plan", layout = ProjectLayout.class)
public class MaintenancePlanView extends VerticalLayout {

}
