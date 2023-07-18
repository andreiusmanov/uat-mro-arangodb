package uz.uat.mro.apps.views.organization.views;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

    private TextField name;
    private TextField country;
    private TextField code;
    private TextField shortName;
    private Binder<Organization> binder = new Binder<>(Organization.class);

    public OrganizationView(OrganizationService service) {
        this.organization = (Organization) MyUtils.getAttribute("organization");
        form();
        binder();
        add(form);
    }

    private void binder() {
        binder.setBean(organization);
        binder.forField(name).bindReadOnly(Organization::getName);
        binder.forField(code).bindReadOnly(Organization::getCode);
        binder.forField(country).bindReadOnly((org) -> org.getCountry().getCode3());
        binder.forField(shortName).bindReadOnly(Organization::getShortName);
    }

    private void form() {
        this.form = new FormLayout();
        this.name = new TextField("Наименование");
        this.country = new TextField("Страна");
        this.code = new TextField("Код");
        this.shortName = new TextField("Краткое наименование");
        form.add(name, country, code, shortName);
    }
}
