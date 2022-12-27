package uz.uat.mro.apps.views.common.views;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.repository.CountriesRepository;
import uz.uat.mro.apps.views.common.layouts.CommonLayout;

@PageTitle(value = "Страны")
@Route(value = "common/countries", layout = CommonLayout.class)
@RouteAlias(value = "", layout = CommonLayout.class)
public class CountriesView extends VerticalLayout {

    private CountriesRepository countryRepo;
    private Grid<Country> countriesGrid;

    /**
     * 
     */
    public CountriesView(CountriesRepository countryRepo) {
        this.countryRepo = countryRepo;
        countriesGrid();
        add(new H3("Страница Стран"), countriesGrid);
    }

    private void countriesGrid() {
        this.countriesGrid = new Grid<>(Country.class);
        Iterable<Country> iterable = countryRepo.findAll();
        List<Country> list = StreamSupport.stream(iterable.spliterator(), true).collect(Collectors.toList());
        this.countriesGrid.setItems(list);

    }
}
