package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.WorkDay;
import uz.uat.mro.apps.model.service.WorkDayService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Расписание")
@Route(value = "common/workdays", layout = AdminLayout.class)
public class WorkDaysView extends VerticalLayout {

    private WorkDayService service;
    private GridCrud<WorkDay> grid;

    /**
     * 
     */
    public WorkDaysView(WorkDayService service) {
        this.service = service;
        grid();
        add(new H3("Рабочие дни"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(WorkDay.class);
        List<WorkDay> list = service.findAll();
        this.grid.getGrid().setItems(list);

        CrudFormFactory<WorkDay> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties( "date");
        factory.setFieldCaptions("Дата");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findAll);
    }
}
