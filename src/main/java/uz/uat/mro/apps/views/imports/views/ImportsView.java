package uz.uat.mro.apps.views.imports.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.imports.layouts.ImportLayout;

@PageTitle("Импорт данных")
@Route(value = "imports", layout = ImportLayout.class)
public class ImportsView extends VerticalLayout {

    private Anchor mpd = new Anchor("import/import-mpd", "MPD");
    private Anchor aircraft = new Anchor("import/aircraft-import", "ВС");
private H3 title = new H3("Импорт данных  ВС и MPD");
    public ImportsView() {
        add(title, mpd, aircraft);
    }
}