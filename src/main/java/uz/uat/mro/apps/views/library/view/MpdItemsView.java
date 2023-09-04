package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.services.mpd.MpdService;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "MPD Items")
@Route(value = "mpd/items", layout = MpdLayout.class)
public class MpdItemsView extends VerticalLayout {

    private MpdService service;
    private Grid<MpdItem> grid;
    private MenuBar menu;
    private MpdEdition edition;

    public MpdItemsView(MpdService service) {
        this.service = service;
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        grid();
        menu();
        add(menu, grid);
    }

    private void grid() {
        this.grid = new Grid<>(MpdItem.class);
        this.grid.setItems(service.findItemsByEdition(edition.getArangoId()));
        this.grid.setColumns("number",
                // "ammReference", "cat", "pgm",
                "zone", "task", "access", // "threshold", "repeat", "apl", "engine",
                "mh", "description", "type");

        this.grid.getColumnByKey("number").setHeader("Номер карты").setTextAlign(ColumnTextAlign.START);
        this.grid.getColumnByKey("zone").setHeader("Зона").setTextAlign(ColumnTextAlign.START);
        this.grid.getColumnByKey("task").setHeader("Задача").setTextAlign(ColumnTextAlign.START);
        this.grid.getColumnByKey("access").setHeader("Доступ").setTextAlign(ColumnTextAlign.START);
        /*
         * this.grid.getColumnByKey("threshold").setAutoWidth(true).setHeader("Порог");
         * this.grid.getColumnByKey("repeat").setAutoWidth(true).setHeader("Повтор");
         * this.grid.getColumnByKey("apl").setAutoWidth(true).setHeader("APL");
         * this.grid.getColumnByKey("engine").setAutoWidth(true).setHeader("Двигатель");
         */
        this.grid.getColumnByKey("mh").setHeader("MH (task only)").setTextAlign(ColumnTextAlign.START);
        this.grid.getColumnByKey("description").setHeader("Описание").setTextAlign(ColumnTextAlign.START);
        this.grid.getColumnByKey("type").setHeader("Тип").setTextAlign(ColumnTextAlign.START);

        grid.getColumns().forEach(column -> column.setAutoWidth(true).setResizable(true));

    }

    private void menu() {
        this.menu = new MenuBar();
        this.menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);

    }
}
