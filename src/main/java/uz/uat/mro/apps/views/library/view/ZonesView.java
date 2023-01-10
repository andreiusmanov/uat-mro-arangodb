package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.library.loayout.MpdLayout;

@PageTitle(value = "Зоны ВС")
@Route(value = "mpd/zones", layout = MpdLayout.class)
public class ZonesView extends VerticalLayout {

}
