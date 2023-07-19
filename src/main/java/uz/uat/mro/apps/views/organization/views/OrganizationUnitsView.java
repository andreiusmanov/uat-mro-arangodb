package uz.uat.mro.apps.views.organization.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;
import uz.uat.mro.apps.model.services.organization.OrganizationService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.organization.layouts.OrganizationLayout;

@PageTitle("Organization Units")
@Route(value = "organization/units", layout = OrganizationLayout.class)
public class OrganizationUnitsView extends VerticalLayout {
    private OrganizationService service;
    private GridCrud<OrganizationUnit> crud;
    private Organization organization;
    private OrganizationUnit organizationUnit;

    public OrganizationUnitsView(OrganizationService service) {
        this.service = service;
        this.organization = (Organization) MyUtils.getAttribute("organization");
        crud();
        add(crud);
    }

    private void crud() {
        this.crud = new GridCrud<>(OrganizationUnit.class);
        this.crud.getGrid().setColumns("name", "code", "description");
        this.crud.getGrid().getColumnByKey("name").setHeader("Name");
        this.crud.getGrid().getColumnByKey("code").setHeader("Code");
        this.crud.getGrid().getColumnByKey("description").setHeader("Description");

        crud.setAddOperation(((organizationUnit) -> service.saveOrganizationUnit(organizationUnit, organization)));
        crud.setUpdateOperation(service::saveOrganizationUnit);
        crud.setDeleteOperation(service::deleteOrganizationUnit);
        crud.setFindAllOperation(() -> service.getOrganizationUnitsByOrganization(organization.getArangoId()));

        crud.getCrudFormFactory().setVisibleProperties("name", "code", "description");

        this.crud.getGrid().addSelectionListener(e -> {
            if (e.getFirstSelectedItem().isPresent()) {
                this.organizationUnit = e.getFirstSelectedItem().get();
            }
        });
    }

}
