package uz.uat.mro.apps.views.main;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import uz.uat.mro.apps.views.common.views.CommonView;
import uz.uat.mro.apps.views.library.view.LibraryView;

public class HomeMenu extends HorizontalLayout {

    public HomeMenu() {
        MenuBar menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem homeItem = menu.addItem("Home", "VaadinIcon.HOME.create()");
        homeItem.getSubMenu().addItem("Common Data", e -> {
            getUI().ifPresent(ui -> ui.navigate(CommonView.class));
        });
        homeItem.getSubMenu().addItem("Library", e -> {
            getUI().ifPresent(ui -> ui.navigate(LibraryView.class));
        });
        add(menu);
    }

}
