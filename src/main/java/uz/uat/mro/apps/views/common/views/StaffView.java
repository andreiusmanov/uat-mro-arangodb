package uz.uat.mro.apps.views.common.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.common.service.PersonService;
import uz.uat.mro.apps.views.common.layouts.DepartmentLayout;

@PageTitle(value = "Сотрудники")
@Route(value = "department/staff", layout = DepartmentLayout.class)
public class StaffView extends VerticalLayout {
    private PersonService service;

    public StaffView(PersonService service) {
        this.service = service;
    }

}
