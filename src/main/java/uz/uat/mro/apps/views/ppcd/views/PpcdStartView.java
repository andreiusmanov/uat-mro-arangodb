package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("PPCD страница")
@Route(value = "ppcd/start", layout=PlanningLayout.class)
public class PpcdStartView extends VerticalLayout{
    
}
