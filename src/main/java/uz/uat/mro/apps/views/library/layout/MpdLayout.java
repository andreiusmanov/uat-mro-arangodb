package uz.uat.mro.apps.views.library.layout;

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
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.view.AccessesView;
import uz.uat.mro.apps.views.library.view.MpdImportView;
import uz.uat.mro.apps.views.library.view.MpdItemsView;
import uz.uat.mro.apps.views.library.view.MpdMhsView;
import uz.uat.mro.apps.views.library.view.MpdTaskcardsView;
import uz.uat.mro.apps.views.library.view.MpdView;
import uz.uat.mro.apps.views.library.view.SubzonesView;
import uz.uat.mro.apps.views.library.view.ZonesView;

public class MpdLayout extends AppLayout {
    private H2 viewTitle;

    public MpdLayout() {
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
        H1 appName = new H1("MPD DATA");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("MPD данные", MpdView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Импорт данных", MpdImportView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Зоны ВС", ZonesView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Субзоны ВС", SubzonesView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Доступы ВС", AccessesView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("Man-Hours", MpdMhsView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("MPD Items", MpdItemsView.class, VaadinIcon.FILE.create()));
        nav.addItem(new AppNavItem("MPD Taskcards", MpdTaskcardsView.class, VaadinIcon.FILE.create()));

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
        MpdEdition edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        return title == null ? "" : title.value() + " " + edition.getNumber() + " dd. " + edition.getDate();
    }

}
