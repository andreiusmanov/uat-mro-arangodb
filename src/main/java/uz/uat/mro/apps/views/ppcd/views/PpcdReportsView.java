package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.ppcd.layouts.PpcdLayout;

@PageTitle(value = "PPCD Отчеты")
@Route(value = "ppcd/reports", layout = PpcdLayout.class)
public class PpcdReportsView extends VerticalLayout{

    public PpcdReportsView(){
        add(new H3(VaadinIcon.LINK.create()));
    }
}
