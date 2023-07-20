package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
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

    public AccessesView(MpdZonesService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        this.model = edition.getModel();
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(MpdAccess.class);
        this.grid.getGrid().setColumns("subzoneString", "model.code", "number", "name", "open", "close", "aplEngine",
                "synthetic", "mmReference");

        this.grid.getGrid().getColumnByKey("subzoneString").setHeader("Код Зоны");
        this.grid.getGrid().getColumnByKey("model.code").setHeader("Модель ВС");
        this.grid.getGrid().getColumnByKey("number").setHeader("Номер");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("open").setHeader("MHs Откр.");
        this.grid.getGrid().getColumnByKey("close").setHeader("MHs Закр.");
        this.grid.getGrid().getColumnByKey("aplEngine").setHeader("APL / Engine");
        this.grid.getGrid().getColumnByKey("synthetic").setHeader("Синт. доступ");
        this.grid.getGrid().getColumnByKey("mmReference").setHeader("MM референс");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAllAccessByModel(model.getArangoId()));

    }

   
}
