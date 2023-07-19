package uz.uat.mro.apps.model.services.organization;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.organization.Facility;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnitName;
import uz.uat.mro.apps.model.alt.organization.edges.HasFacility;
import uz.uat.mro.apps.model.alt.organization.edges.HasUnit;
import uz.uat.mro.apps.model.alt.organization.repositories.FacilityRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.HasFacilityRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.HasOrganizationUnitRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationUnitNameRepo;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationUnitRepo;
import uz.uat.mro.apps.model.repositories.CountryRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private FacilityRepo facilityRepo;
    private HasOrganizationUnitRepo hasUnitRepo;
    private OrganizationRepo organizationRepo;
    private OrganizationUnitRepo organizationUnitRepo;
    private OrganizationUnitNameRepo organizationUnitNameRepo;
    private HasFacilityRepo hasFacilityRepo;
    private CountryRepo countryRepo;



    public List<Facility> findAllFacilities() {
        return StreamSupport.stream(facilityRepo.findAll().spliterator(), false).toList();
    }

    public List<Facility> getFacilitiesByOrganization(String organization) {
        List<Facility> facilities = facilityRepo.getFacilitiesByOrganization(organization);
        return facilities;
    }


    public List<OrganizationUnit> getOrganizationUnitsByOrganization(String organization) {
        return StreamSupport.stream(organizationUnitRepo.getOrganizationUnitsByOrganization(organization).spliterator(),false).toList();    
    }

    public Facility saveFacility(Facility facility, Organization organization) {
        Facility savedFacility = facilityRepo.save(facility);
        HasFacility hasFacility = new HasFacility();
        hasFacility.setOrganization(organization);
        hasFacility.setFacility(savedFacility);
        saveHasFacility(hasFacility);
        return savedFacility;
    };



    public OrganizationUnit saveOrganizationUnit(OrganizationUnit unit, Organization organization) {
        OrganizationUnit savedUnit = organizationUnitRepo.save(unit);
        HasUnit hasUnit = new HasUnit();
        hasUnit.setOrganization(organization);
        hasUnit.setOrganizationUnit(unit);
        saveHasOrganizationUnit(hasUnit);
        return savedUnit;
    };


    
    private void saveHasOrganizationUnit(HasUnit hasUnit) {
        hasUnitRepo.save(hasUnit);
    }

    
    public HasFacility saveHasFacility(HasFacility hasFacility) {
        return hasFacilityRepo.save(hasFacility);
    };

    // findAllCountries() is used in
    public List<Country> findAllCountries() {
        return StreamSupport.stream(countryRepo.findAll().spliterator(), false).toList();
    }

    public Facility saveFacility(Facility facility) {
        return facilityRepo.save(facility);
    };

    public void deleteFacility(Facility facility) {
        facilityRepo.delete(facility);
    };

    public List<Facility> findAllFacilities(Organization organization) {
        return StreamSupport.stream(facilityRepo.findAll().spliterator(), false).toList();
    }

    public Facility getFacilityById(String id) {
        return facilityRepo.findById(id).get();
    };

    public Organization saveOrganization(Organization organization) {
        return organizationRepo.save(organization);
    };

    public void deleteOrganization(Organization organization) {
        organizationRepo.delete(organization);
    };

    // findAllOrganizations() is used in
    // src/main/java/uz/uat/mro/apps/views/organization/views/OrganizationsView.java
    public List<Organization> findAllOrganizations() {
        return StreamSupport.stream(organizationRepo.findAll().spliterator(), false).toList();
    }

    public OrganizationUnit saveOrganizationUnit(OrganizationUnit organizationUnit) {
        return organizationUnitRepo.save(organizationUnit);
    };

    public void deleteOrganizationUnit(OrganizationUnit organizationUnit) {
        organizationUnitRepo.delete(organizationUnit);
    };

    public List<OrganizationUnitName> findAllOrganizationUnitNames() {
        return StreamSupport.stream(organizationUnitNameRepo.findAll().spliterator(), false).toList();
    }

    public OrganizationUnitName findOrganizationUnitNameById(String id) {
        return organizationUnitNameRepo.findById(id).get();
    }
}
