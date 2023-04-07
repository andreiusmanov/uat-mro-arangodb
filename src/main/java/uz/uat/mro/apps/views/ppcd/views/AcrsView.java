package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.docs.Acr;
import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("ACR")
@Route(value = "ppcd/acr", layout = PlanningLayout.class)
public class AcrsView extends VerticalLayout {

private Project project;
private MenuBar menu;
private Grid<Acr> grid;


}
