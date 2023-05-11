package uz.uat.mro.apps.views.ppcd.layouts;

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
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.ppcd.views.AcrsView;
import uz.uat.mro.apps.views.ppcd.views.ClosedCardsView;
import uz.uat.mro.apps.views.ppcd.views.NonRoutineCardsView;
import uz.uat.mro.apps.views.ppcd.views.PpcdReportsView;
import uz.uat.mro.apps.views.ppcd.views.JobcardsView;
import uz.uat.mro.apps.views.ppcd.views.MroCoefficentView;
import uz.uat.mro.apps.views.ppcd.views.PpcdStartView;
import uz.uat.mro.apps.views.ppcd.views.ProjectsView;

public class PpcdLayout extends AppLayout {
    private H2 viewTitle;
    private Project project;

    public PpcdLayout() {
        this.project = (Project) MyUtils.getAttribute(Keys.PROJECT);
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
        AppNav nav = new AppNav();
        nav.addItem(new AppNavItem("Контракты", ProjectsView.class, "la la-file"));
        nav.addItem(new AppNavItem("Отчеты", PpcdReportsView.class, VaadinIcon.LINE_BAR_CHART.create()));
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
