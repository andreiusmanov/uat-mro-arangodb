package uz.uat.mro.apps.views.common.layouts;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import uz.uat.mro.apps.components.appnav.AppNav;
import uz.uat.mro.apps.components.appnav.AppNavItem;
import uz.uat.mro.apps.views.common.views.CountriesView;
import uz.uat.mro.apps.views.common.views.CurrenciesView;
import uz.uat.mro.apps.views.common.views.FirmsView;
import uz.uat.mro.apps.views.common.views.StationsView;
import uz.uat.mro.apps.views.common.views.UomsView;
import uz.uat.mro.apps.views.common.views.WorkDaysView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class AdminLayout extends AppLayout {

    private H2 viewTitle;

    public AdminLayout() {
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

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Общие данные", CountriesView.class, VaadinIcon.GLOBE.create()));
        nav.addItem(new AppNavItem("Страны", CountriesView.class, VaadinIcon.GLOBE.create()));
        nav.addItem(new AppNavItem("Station", StationsView.class, VaadinIcon.OFFICE.create()));
        nav.addItem(new AppNavItem("Валюты", CurrenciesView.class, VaadinIcon.MONEY.create()));
        nav.addItem(new AppNavItem("Организации", FirmsView.class, VaadinIcon.OFFICE.create()));
        nav.addItem(new AppNavItem("Ед. измерения", UomsView.class, VaadinIcon.RECORDS.create()));
        nav.addItem(new AppNavItem("Расписание", WorkDaysView.class, VaadinIcon.RECORDS.create()));

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
