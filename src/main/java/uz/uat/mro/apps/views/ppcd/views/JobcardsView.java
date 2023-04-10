package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.ppcd.layouts.PlanningLayout;

@PageTitle("Рабочие карты")
@Route(value = "ppcd/jobcards", layout = PlanningLayout.class)
public class JobcardsView extends VerticalLayout {
    private MenuBar menu;
    private MenuBar actionsMenu;
    private TextField searchField;

    /**
     * 
     */
    public JobcardsView() {
        menu();
        actionsMenu();
        searchField();
        add(menu, searchField, actionsMenu);
    }

    private void actionsMenu() {
        this.actionsMenu = new MenuBar();
        actionsMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        actionsMenu.addItem("Открыть");
        actionsMenu.addItem("Отложить");
        actionsMenu.addItem("Закрыть");
    }

    private void searchField() {
        this.searchField = new TextField("", "", "Поиск");
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menu.addItem("Рабочие карты");
        menu.addItem("Non-routine карты");
        menu.addItem("EO карты");
    }

}
