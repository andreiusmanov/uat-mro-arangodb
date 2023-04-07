package uz.uat.mro.apps.views.ppcd.views;

import org.vaadin.crudui.layout.impl.VerticalCrudLayout;

import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("Рабочие карты")
@Route(value = "ppcd/jobcards", layout = PlanningLayout.class)
public class JobcardsView extends VerticalLayout {
    private MenuBar menu;

    /**
     * 
     */
    public JobcardsView() {
        menu();
    }

    private void menu() {
        menu.addItem("Открытые карты");
        menu.addItem("Отложенные карты");
        menu.addItem("Закрытые карты");

    }

}
