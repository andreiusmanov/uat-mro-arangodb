package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.common.Workday;
import uz.uat.mro.apps.model.services.common.CommonService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Расписание")
@Route(value = "common/workdays", layout = AdminLayout.class)
public class WorkdaysView extends VerticalLayout {

    private CommonService service;
    private GridCrud<Workday> grid;

    /**
     * 
     */
    public WorkdaysView(CommonService service) {
        this.service = service;
        grid();
        add(new H3("Рабочие дни"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Workday.class);
        List<Workday> list = service.findAllWorkDays();
        this.grid.getGrid().setItems(list);

        CrudFormFactory<Workday> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("date");
        factory.setFieldCaptions("Дата");

        grid.setAddOperation(service::saveWorkDay);
        grid.setUpdateOperation(service::saveWorkDay);
        grid.setDeleteOperation(service::deleteWorkDay);
        grid.setFindAllOperation(service::findAllWorkDays);
    }
}
