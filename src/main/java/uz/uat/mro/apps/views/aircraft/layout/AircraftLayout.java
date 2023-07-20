package uz.uat.mro.apps.views.aircraft.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import uz.uat.mro.apps.components.appnav.AppNav;
import uz.uat.mro.apps.components.appnav.AppNavItem;
import uz.uat.mro.apps.views.aircraft.view.AircraftModelsView;
import uz.uat.mro.apps.views.aircraft.view.AircraftsView;
import uz.uat.mro.apps.views.aircraft.view.MajorModelsView;
import uz.uat.mro.apps.views.common.views.CommonView;
import uz.uat.mro.apps.views.library.view.LibraryView;
import uz.uat.mro.apps.views.main.HomeMenu;

/**
 * The main view is a top-level placeholder for other views.
 */
public class AircraftLayout extends AppLayout {

    private H2 viewTitle;
    private HomeMenu menu = new HomeMenu();

    public AircraftLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("UAT MRO");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());
        menu.setWidthFull();
        addToDrawer(header, menu, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Основные Типы ВС", MajorModelsView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Модели ВС", AircraftModelsView.class, VaadinIcon.AIRPLANE.create()));
        nav.addItem(new AppNavItem("Воздушные Суда", AircraftsView.class, VaadinIcon.AIRPLANE.create()));

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
