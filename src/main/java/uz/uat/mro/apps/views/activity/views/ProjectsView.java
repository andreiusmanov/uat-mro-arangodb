package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.ProjectService;
import uz.uat.mro.apps.views.common.layouts.InitialLayout;

@PageTitle(value = "Список проектов")
@Route(value = "activity/projects", layout = InitialLayout.class)
public class ProjectsView extends VerticalLayout {
    private ProjectService service;
    private Grid<Project> grid;
    private MenuBar menu;

    public ProjectsView(ProjectService service) {
        this.service = service;
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {

    }

    private void menu() {

    }

}
