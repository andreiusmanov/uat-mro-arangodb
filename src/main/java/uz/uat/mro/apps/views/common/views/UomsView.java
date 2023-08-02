package uz.uat.mro.apps.views.common.views;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.common.Uom;
import uz.uat.mro.apps.model.services.common.CommonService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Ед. Измерения")
@Route(value = "common/uoms", layout = AdminLayout.class)
public class UomsView extends VerticalLayout {

    private CommonService service;
    private GridCrud<Uom> grid;

    /**
     * 
     */
    public UomsView(CommonService service) {
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

        grid.setAddOperation(service::saveUom);
        grid.setUpdateOperation(service::saveUom);
        grid.setDeleteOperation(service::deleteUom);
        grid.setFindAllOperation(service::findAllUom);
    }
}
