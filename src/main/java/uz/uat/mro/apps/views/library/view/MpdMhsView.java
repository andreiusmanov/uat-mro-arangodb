package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdMh;
import uz.uat.mro.apps.model.library.service.MpdZonesService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "MPD Man-Hours")
@Route(value = "library/man-hours", layout = MpdLayout.class)
public class MpdMhsView extends VerticalLayout {

    private MpdZonesService service;
    private GridCrud<MpdMh> grid;
    private MpdEdition edition;

    public MpdMhsView(MpdZonesService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        grid();
        add(grid);

    }

    private void grid() {
        this.grid = new GridCrud<>(MpdMh.class);
        this.grid.getGrid().setColumns("mpdItem.number", "openMh", "closeMh", "totalMh", "accessString");

        this.grid.setFindAllOperation(() -> service.findMhByEdition(edition.getArangoId()));

    }

}
