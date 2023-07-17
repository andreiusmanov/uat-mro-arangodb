package uz.uat.mro.apps.views.organization.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.organization.Facility;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.services.organization.OrganizationService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.organization.layouts.OrganizationLayout;

@PageTitle(value = "Объекты")
@Route(value = "organization/facilities", layout = OrganizationLayout.class)
public class FacilitiesView extends VerticalLayout {

    private OrganizationService service;
    private Organization organization;
    private Facility facility;
    private GridCrud<Facility> grid;

    public FacilitiesView(OrganizationService service) {
        this.service = service;
        this.organization = (Organization) MyUtils.getAttribute("organization");
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Facility.class);
        this.grid.getGrid().setColumns("name", "code", "description");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("description").setHeader("Описание");
    
    grid.setAddOperation(service::saveFacility);
    grid.setUpdateOperation(service::saveFacility);
    grid.setDeleteOperation(service::deleteFacility);
  //  grid.setFindAllOperation(service::findAllFacilities(organization));
    
    
    
    }

}
