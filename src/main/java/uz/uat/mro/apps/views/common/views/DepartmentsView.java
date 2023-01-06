package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.service.DepartmentService;
import uz.uat.mro.apps.views.common.layouts.FirmLayout;

@PageTitle(value = "Отделы")
@Route(value = "firm/departments", layout = FirmLayout.class)
public class DepartmentsView extends VerticalLayout {
    private DepartmentService service;
    private Firm firm;
    private GridCrud<Department> grid;

    public DepartmentsView(DepartmentService service) {
        this.service = service;
        grid();
        add(new H3("Организации"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Department.class);
        List<Department> list = service.findByFirm(firm);
        this.grid.getGrid().setItems(list);

        CrudFormFactory<Department> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("firm", "code", "name", "shortName");
        factory.setFieldCaptions("Организация", "Код", "Наименование", "Аббревиатура");
        factory.setDisabledProperties("firm");
        factory.setFieldProvider("firm", user -> {
            TextField firmField = new TextField();
            firmField.setValue(firm.getShortName());
            return firmField;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findByFirm(firm));
    }

}
