package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "Доступы")
@Route(value = "mpd/accesses", layout = MpdLayout.class)
public class AccessesView extends VerticalLayout {

}
