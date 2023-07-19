package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.library.layout.LibraryLayout;

@PageTitle(value = "Библиотека")
@Route(value = "library", layout = LibraryLayout.class)
public class LibraryView extends VerticalLayout {

}
