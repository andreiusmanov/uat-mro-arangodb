package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.grid.dataview.GridDataView;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.service.MpdZonesService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Доступы")
@Route(value = "mpd/accesses", layout = MpdLayout.class)
public class AccessesView extends VerticalLayout {
    private MpdZonesService service;
    private MpdEdition edition;
    private MajorModel model;
    private GridCrud<MpdAccess> grid;
    private MenuBar menu;
    private ListDataView listDataView;

    public AccessesView(MpdZonesService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        this.model = edition.getModel();
    }

    private void grid() {
        this.grid = new GridCrud<>(MpdAccess.class);
        this.listDataView = grid.getGrid().getListDataView();
        this.grid.getGrid().setColumns("zone.code", "subzone.code", "number", "open", "close", "aplEngine", "name",
                "synthetic", "mmReeference");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::save);
        grid.setFindAllOperation(() -> service.findAllAccessByModel(model));

    }

    private void menu() {
    }

}
