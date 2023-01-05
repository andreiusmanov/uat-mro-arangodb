package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.entity.Station;
import uz.uat.mro.apps.model.entity.Uom;
import uz.uat.mro.apps.model.service.StationService;
import uz.uat.mro.apps.model.service.UomService;
import uz.uat.mro.apps.views.common.layouts.CommonLayout;

@PageTitle(value = "Ед. Измерения")
@Route(value = "common/uoms", layout = CommonLayout.class)
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

        CrudFormFactory<Uom> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties( "code");
        factory.setFieldCaptions("Код");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);
    }
}
