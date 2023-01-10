package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.library.loayout.LibraryLayout;

@PageTitle(value = "MPD издания")
@Route(value = "library/mpd-editions", layout = LibraryLayout.class)
public class MpdEditionsView extends VerticalLayout {

}
