package uz.uat.mro.apps.views.reports;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.marketing.layouts.MarketingLayout;

@PageTitle(value = "Marketing Reports")
@Route(value = "marketing/reports", layout = MarketingLayout.class)
public class MarketingReportsView extends VerticalLayout {

    public MarketingReportsView() {
        add(new H3(VaadinIcon.CONNECT.create()));
    }

}
