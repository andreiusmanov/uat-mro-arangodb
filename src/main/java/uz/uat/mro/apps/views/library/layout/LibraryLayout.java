package uz.uat.mro.apps.views.library.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import uz.uat.mro.apps.components.appnav.AppNav;
import uz.uat.mro.apps.components.appnav.AppNavItem;
import uz.uat.mro.apps.views.aircraft.view.AircraftModelsView;
import uz.uat.mro.apps.views.library.view.Ata100ChaptersView;
import uz.uat.mro.apps.views.library.view.LibraryView;
import uz.uat.mro.apps.views.library.view.MpdEditionsView;
import uz.uat.mro.apps.views.main.MainView;

public class LibraryLayout extends AppLayout {

    private H2 viewTitle;
    private MenuBar menu;

    public LibraryLayout() {
        setPrimarySection(Section.DRAWER);
        menu();
        addDrawerContent();
        addHeaderContent();
        this.setDrawerOpened(true);
    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem menuItem = this.menu.addItem("Меню");
        menuItem.getSubMenu().addItem("Общие данные", e -> {
            getUI().ifPresent(ui -> ui.navigate(MainView.class));
        });
        menuItem.getSubMenu().addItem("Библиотека", e -> {
            getUI().ifPresent(ui -> ui.navigate(LibraryView.class));
        });

    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("UAT БИБЛИОТЕКА");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, menu, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Модели ВС", AircraftModelsView.class, VaadinIcon.AIRPLANE.create()));
        nav.addItem(new AppNavItem("ATA 100 CHAPTERS", Ata100ChaptersView.class, VaadinIcon.BOOK.create()));
        nav.addItem(new AppNavItem("MPD Издания", MpdEditionsView.class, VaadinIcon.BOOK.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
