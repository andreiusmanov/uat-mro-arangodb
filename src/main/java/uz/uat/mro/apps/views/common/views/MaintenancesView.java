package uz.uat.mro.apps.views.common.views;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.common.Maintenance;
import uz.uat.mro.apps.model.services.common.CommonService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle("Типы обслуживания")
@Route(value = "maintenances", layout = AdminLayout.class)
public class MaintenancesView extends VerticalLayout {
    private CommonService service;
    private GridCrud<Maintenance> crud;

    public MaintenancesView(CommonService service) {
        this.service = service;
        grid();
        add(crud);
    }

    private void grid() {
        crud = new GridCrud<>(Maintenance.class);
        crud.setFindAllOperation(service::findAllMaintenances);
        crud.setAddOperation(service::saveMaintenance);
        crud.setUpdateOperation(service::saveMaintenance);
        crud.setDeleteOperation(service::deleteMaintenance);
        crud.getGrid().setColumns("code", "index", "description");
        crud.getCrudFormFactory().setVisibleProperties("code", "index", "description");
    }

}
