package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.services.mpd.MpdService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "MPD Taskcards")
@Route(value = "mpd/taskcards", layout = MpdLayout.class)
public class MpdTaskcardsView extends VerticalLayout {

    private MpdService service;
    private Grid<MpdTaskcard> grid;
    private MenuBar menu;
    private MpdEdition edition;

    public MpdTaskcardsView(MpdService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new Grid<>(MpdTaskcard.class);
        this.grid.setItems(service.findCardsByEdition(edition.getArangoId()));
        this.grid.setColumns("number", "mpdItemString", "mrbItem", "task", "title", "relatedTasksString");
        this.grid.getColumnByKey("number").setHeader("Number");
        this.grid.getColumnByKey("mpdItemString").setHeader("MPD Item");
        this.grid.getColumnByKey("mrbItem").setHeader("MRB Item");
        this.grid.getColumnByKey("task").setHeader("Task");
        this.grid.getColumnByKey("title").setHeader("Title");
        this.grid.getColumnByKey("relatedTaskString").setHeader("Related Tasks");
    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);

    }
}
