package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.MaterialItem;
import uz.uat.mro.apps.model.activity.service.MaterialItemService;
import uz.uat.mro.apps.model.marketing.entity.Project;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle("Material List")
@Route(value = "project/material-list", layout = ProjectLayout.class)
public class MaterialListView extends VerticalLayout {
    private MaterialItemService service;
    private Project project;
    private Grid<MaterialItem> grid;

    /**
     * @param service
     */
    public MaterialListView(MaterialItemService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
        grid();
        add(grid);
    }

    private void grid() {
        this.grid = new Grid<>(MaterialItem.class);
        this.grid.setColumns("name", "partNumber", "quantity", "uom.id", "description");
        this.grid.getColumnByKey("name").setHeader("Наименование");
        this.grid.getColumnByKey("partNumber").setHeader("Part Number");
        this.grid.getColumnByKey("quantity").setHeader("Количество");
        this.grid.getColumnByKey("uom.id").setHeader("ед. изм.");
        this.grid.getColumnByKey("description").setHeader("Наименование");

        grid.setItems(service.findAllByProject(project.getArangoId()));

    }

}
