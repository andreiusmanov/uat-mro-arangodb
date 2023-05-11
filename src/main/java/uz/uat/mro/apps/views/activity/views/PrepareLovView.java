package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.service.MaterialItemService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Импорт данных LOV MC")
@Route(value = "ppcd/lovmc", layout = ProjectLayout.class)
public class PrepareLovView extends VerticalLayout {

    private MaterialItemService service;
    private Project project;
    private MenuBar menu;
    private MenuItem importItem;
    private MenuItem revisionItem;

    public PrepareLovView(MaterialItemService service) {
        this.service = service;
        this.project = (Project) MyUtils.getAttribute("project");
        menu();
        add(menu);
    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        importItem = menu.addItem("Импорт", "Импорт LOV Maintenance Cards");
        revisionItem = menu.addItem("Ревизия", "Регистрация ревизии LOV Maintenance Cards");

        importItem.addClickListener(click -> {
            Notification.show("Arranging import of LOV MC");
        });

        revisionItem.addClickListener(click -> {
            Notification.show("Arranging import of LOV MC");
        });
    }

}
