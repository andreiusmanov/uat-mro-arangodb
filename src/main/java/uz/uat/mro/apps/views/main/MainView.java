package uz.uat.mro.apps.views.main;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("About")
@Route(value = "about")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {

    private Anchor marketing;
    private Anchor ppcd;
    private Anchor production;
    private Anchor logistics;
    private H3 title;

    public MainView() {
        setSpacing(false);
        this.marketing = new Anchor("marketing", "Marketing");
        this.ppcd = new Anchor("ppcd/projects", "PPCD");
        add(marketing, ppcd);
    }

}
