package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.entity.Station;
import uz.uat.mro.apps.model.service.StationService;
import uz.uat.mro.apps.views.common.layouts.CommonLayout;

@PageTitle(value = "Stations")
@Route(value = "common/stations", layout = CommonLayout.class)
public class StationsView extends VerticalLayout {

    private StationService service;
    private GridCrud<Station> grid;

    /**
     * 
     */
    public StationsView(StationService service) {
        this.service = service;
        grid();
        add(new H3("Страны"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Station.class);
        List<Station> list = service.findStations();
        this.grid.getGrid().setItems(list);

        CrudFormFactory<Station> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("country", "code", "name");
        factory.setFieldCaptions("Страна", "Код", "Наименование");

        factory.setFieldProvider("country", user -> {
            ComboBox<Country> countries = new ComboBox<>();
            countries.setItems(service.findAllCountries());
            countries.setItemLabelGenerator(e -> e.getShortName());
            return countries;
        });


        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findStations);
    }
}
