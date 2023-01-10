package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.library.service.MpdImportService;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle("MPD данные")
@Route(value = "mpd/data", layout = MpdLayout.class)
public class MpdDataView extends VerticalLayout {
    private MpdImportService service;
    private H3 importPdfTitle = new H3("Импорт данных из PDF");
    private H3 importXlsTitle = new H3("Импорт данных из Excel");
    /**
     * 
     */
    public MpdDataView(MpdImportService service) {
    this.service = service;

    
    };



}
