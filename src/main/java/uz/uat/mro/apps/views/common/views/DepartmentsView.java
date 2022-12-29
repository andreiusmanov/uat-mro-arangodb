package uz.uat.mro.apps.views.common.views;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.common.layouts.FirmLayout;

@PageTitle(value = "")
@Route(value = "firm/departments", layout = FirmLayout.class)
public class DepartmentsView extends VerticalLayout {

    /**
     * 
     */
    public DepartmentsView() {
        add(new H3("Отделы"));
    }

}
