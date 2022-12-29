package uz.uat.mro.apps.views.common.views;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.common.layouts.DepartmentLayout;

@PageTitle(value = "")
@Route(value = "department/sectors", layout = DepartmentLayout.class)
public class SectorView extends VerticalLayout {

    /**
     * 
     */
    public SectorView() {
        add(new H3("Участки/смены"));
    }

}
