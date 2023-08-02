package uz.uat.mro.apps.views.common.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.common.Sector;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;
import uz.uat.mro.apps.model.services.common.CommonService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.DepartmentLayout;

@PageTitle(value = "Участки / Бригады")
@Route(value = "department/sectors", layout = DepartmentLayout.class)
public class SectorsView extends VerticalLayout {

    private CommonService service;
    private OrganizationUnit department;
    private Sector sector;
    private GridCrud<Sector> grid;

    public SectorsView(CommonService service) {
        this.service = service;
        this.department = (OrganizationUnit) MyUtils.getAttribute("department");
        grid();
        add(new H3("Участки/смены отдела " + department.getShortName()), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Sector.class);
        this.grid.getGrid().setColumns("name", "code", "shortName");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");
        this.grid.getGrid().getColumnByKey("shortName").setHeader("Кратк. Наименование");

        CrudFormFactory<Sector> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("department", "code", "name", "shortName");
        factory.setFieldCaptions("Отдел", "Код", "Наименование", "Аббревиатура");
        factory.setFieldProvider("department", user -> {
            ComboBox<OrganizationUnit> cb = new ComboBox<>("Отдел", department);
            cb.setItemLabelGenerator(e -> e.getShortName());
            return cb;
        });

        grid.setAddOperation(service::saveSector);
        grid.setUpdateOperation(service::saveSector);
        grid.setDeleteOperation(service::deleteSector);
        grid.setFindAllOperation(() -> service.findByDepartmentId(department.getArangoId()));

        grid.getCrudFormFactory().setNewInstanceSupplier(() -> {
            Sector dept = new Sector();
            dept.setDepartment(department);
            return dept;
        });

        grid.getGrid().addSelectionListener(e -> {
            this.sector = e.getFirstSelectedItem().orElse(null);
            boolean res = e.getFirstSelectedItem().isPresent();
            // sectors.setEnabled(res);
        });

    }
}
