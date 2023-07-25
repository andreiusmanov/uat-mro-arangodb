package uz.uat.mro.apps.views.imports.layouts;

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
import uz.uat.mro.apps.views.imports.views.AircraftImportView;
import uz.uat.mro.apps.views.imports.views.ImportsView;
import uz.uat.mro.apps.views.imports.views.MpdImportView;

public class ImportLayout extends AppLayout {
    private H2 viewTitle;

    public ImportLayout() {
        setPrimarySection(Section.DRAWER);
        setDrawerOpened(true);
        addDrawerContent();
        addHeaderContent();
        this.setDrawerOpened(true);
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("IMPORT DATA");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Об импорте данных", ImportsView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Импорт данных ВС", AircraftImportView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Импорт данных MPD", MpdImportView.class, VaadinIcon.FILE.create()));

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
