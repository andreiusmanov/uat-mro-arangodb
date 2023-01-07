package uz.uat.mro.apps.views.common.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Sector;
import uz.uat.mro.apps.model.service.SectorService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.common.layouts.DepartmentLayout;

@PageTitle(value = "")
@Route(value = "department/sectors", layout = DepartmentLayout.class)
public class SectorsView extends VerticalLayout {

    private SectorService service;
    private Department department;
    private Sector sector;
    private GridCrud<Sector> grid;

    public SectorsView(SectorService service) {
        this.service = service;
        this.department = (Department) MyUtils.getAttribute("department");
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
            ComboBox<Department> cb = new ComboBox<>("Отдел", department);
            cb.setItemLabelGenerator(e -> e.getShortName());
            return cb;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findByDepartment(department.getArangoId()));

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
