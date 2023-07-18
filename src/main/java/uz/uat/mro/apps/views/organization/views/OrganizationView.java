package uz.uat.mro.apps.views.organization.views;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.organization.OrganizationService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.organization.layouts.OrganizationLayout;

@PageTitle(value = "Организация")
@Route(value = "organizations/organization", layout = OrganizationLayout.class)

public class OrganizationView extends VerticalLayout {
    private Organization organization;
    private FormLayout form;

    public OrganizationView(OrganizationService service) {
        this.organization = (Organization) MyUtils.getAttribute("organization");
        form();
        add(form);
    }

    private void form() {
        this.form = new FormLayout();
        Binder<Organization> binder = new Binder<>(Organization.class);
        binder.setBean(organization);
        binder.bindInstanceFields(form);
    }
}
