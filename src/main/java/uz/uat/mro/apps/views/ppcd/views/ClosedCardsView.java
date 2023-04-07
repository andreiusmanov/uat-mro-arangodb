package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("Open Job cards")
@Route(value = "ppcd/closedcards", layout = PlanningLayout.class)
public class ClosedCardsView extends VerticalLayout {

}
