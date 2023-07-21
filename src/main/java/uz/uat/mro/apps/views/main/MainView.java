package uz.uat.mro.apps.views.main;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("About")
@Route(value = "about")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {

    private Anchor common;
    private Anchor imports;
    private Anchor library;
    private Anchor marketing;
    private Anchor ppcd;
    //private Anchor production;
    //private Anchor logistics;
    //private H3 title;

    public MainView() {
        setSpacing(false);
        this.marketing = new Anchor("marketing", "Marketing");
        this.ppcd = new Anchor("ppcd/projects", "PPCD");
        this.common = new Anchor("common", "Общие данные");
        this.library = new Anchor("library", "Библиотека");
        this.imports = new Anchor("imports", "Импорт внешних данных");
        add(common, imports, library, marketing, ppcd);
    }

}
