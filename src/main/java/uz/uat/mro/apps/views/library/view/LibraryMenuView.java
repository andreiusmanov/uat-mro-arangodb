package uz.uat.mro.apps.views.library.view;

import org.springframework.boot.actuate.endpoint.web.Link;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "library")
public class LibraryMenuView extends VerticalLayout{
  
private Link ataChaptersLink;
private Link majorModelsLink;
private Link mpdEditionLink;

    public LibraryMenuView() {
        super();
    }


}
