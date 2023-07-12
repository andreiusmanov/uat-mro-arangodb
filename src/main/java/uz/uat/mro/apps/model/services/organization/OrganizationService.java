package uz.uat.mro.apps.model.services.organization;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.organization.Facility;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;
import uz.uat.mro.apps.model.alt.organization.repositories.FacilityRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationUnitNameRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationUnitRepo;
import uz.uat.mro.apps.model.repositories.OrganizationRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private FacilityRepo facilityRepo;
    private OrganizationRepo organizationRepo;
    private OrganizationUnitRepo organizationUnitRepo;
    private OrganizationUnitNameRepo organizationUnitName;

    public Facility saveFacility(Facility facility) {
        return facilityRepo.save(facility);
    };

    public void deleteFacility(Facility facility) {
        facilityRepo.delete(facility);
    };

    public Facility getFacilityById(String id) {
        return facilityRepo.findById(id).get();
    };

    public Organization saveOrganization(Organization organization) {
        return organizationRepo.save(organization);
    };

    public void deleteOrganization(Organization organization) {
        organizationRepo.delete(organization);
    };

    public OrganizationUnit saveOrganizationUnit(OrganizationUnit organizationUnit) {
        return organizationUnitRepo.save(organizationUnit);
    };

    public void deleteOrganizationUnit(OrganizationUnit organizationUnit) {
        organizationUnitRepo.delete(organizationUnit);
    };

}
