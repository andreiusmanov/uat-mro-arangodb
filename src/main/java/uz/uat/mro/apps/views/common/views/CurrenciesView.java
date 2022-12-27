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

import uz.uat.mro.apps.model.entity.Currency;
import uz.uat.mro.apps.model.repository.CurrenciesRepository;
import uz.uat.mro.apps.views.common.layouts.CommonLayout;

@PageTitle(value = "Страны")
@Route(value = "common/currencies", layout = CommonLayout.class)
@RouteAlias(value = "", layout = CommonLayout.class)
public class CurrenciesView extends VerticalLayout {

    private CurrenciesRepository currencyRepo;
    private Grid<Currency> currenciesGrid;

    /**
     * 
     */
    public CurrenciesView(CurrenciesRepository countryRepo) {
        this.currencyRepo = countryRepo;
        countriesGrid();
        add(new H3("Валюты"), currenciesGrid);
    }

    private void countriesGrid() {
        this.currenciesGrid = new Grid<>(Currency.class);
        Iterable<Currency> iterable = currencyRepo.findAll();
        List<Currency> list = StreamSupport.stream(iterable.spliterator(), true).collect(Collectors.toList());
        this.currenciesGrid.setItems(list);
    }
}
