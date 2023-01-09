package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.common.entity.Uom;
import uz.uat.mro.apps.model.common.service.UomService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Ед. Измерения")
@Route(value = "common/uoms", layout = AdminLayout.class)
public class UomsView extends VerticalLayout {

    private UomService service;
    private GridCrud<Uom> grid;

    /**
     * 
     */
    public UomsView(UomService service) {
        this.service = service;
        grid();
        add(new H3("Ед. Измерения"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Uom.class);
        List<Uom> list = service.findAll();
        this.grid.getGrid().setItems(list);
        this.grid.getGrid().setColumns("code");
        this.grid.getGrid().getColumnByKey("code").setHeader("Код");

        CrudFormFactory<Uom> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("code");
        factory.setFieldCaptions("Код");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);
    }
}
