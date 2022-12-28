package uz.uat.mro.apps.views.common.views;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.repository.FirmsRepository;

@PageTitle(value = "Организация")
@Route(value = "common/firms")
public class FirmsView extends VerticalLayout {
    private FirmsRepository currencyRepo;
    private Grid<Firm> firmsGrid;


    public FirmsView(FirmsRepository countryRepo) {
        this.currencyRepo = countryRepo;
        countriesGrid();
        add(new H3("Организации"), firmsGrid);
    }

    private void countriesGrid() {
        this.firmsGrid = new Grid<>(Firm.class);
        Iterable<Firm> iterable = currencyRepo.findAll();
        List<Firm> list = StreamSupport.stream(iterable.spliterator(), true).collect(Collectors.toList());
        this.firmsGrid.setItems(list);
    }

}
