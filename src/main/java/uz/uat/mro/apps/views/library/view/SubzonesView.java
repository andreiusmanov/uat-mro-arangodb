package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Субзоны")
@Route(value = "mpd/subzones", layout = MpdLayout.class)
public class SubzonesView extends VerticalLayout {
}
