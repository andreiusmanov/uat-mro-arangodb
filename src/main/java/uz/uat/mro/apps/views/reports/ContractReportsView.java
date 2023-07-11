package uz.uat.mro.apps.views.reports;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.marketing.layouts.ContractLayout;

@PageTitle(value = "Contract Reports")
@Route(value = "contract/reports", layout = ContractLayout.class)
public class ContractReportsView extends VerticalLayout {

    public ContractReportsView() {
        add(new H3(VaadinIcon.CONNECT.create()));
    }
}
