package uz.uat.mro.apps.views.common.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

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
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Uom.class);
        this.grid.getGrid().setColumns("id", "description");
        this.grid.getGrid().getColumnByKey("id").setHeader("Код");
        this.grid.getGrid().getColumnByKey("description").setHeader("Описанние");

        CrudFormFactory<Uom> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("id", "description");
        factory.setFieldCaptions("Код", "Описание");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);
    }
}
