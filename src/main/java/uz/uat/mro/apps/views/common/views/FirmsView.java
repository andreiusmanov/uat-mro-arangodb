package uz.uat.mro.apps.views.common.views;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.repository.FirmsRepository;

@PageTitle(value = "Организация")
@Route(value = "common/firms")
public class FirmsView extends VerticalLayout {
    private FirmsRepository firmsRepo;
    private GridCrud<Firm> firmsGrid;

    public FirmsView(FirmsRepository firmsRepo) {
        this.firmsRepo = firmsRepo;
        firmsGrid();
        add(new H3("Организации"), firmsGrid);
    }

    private void firmsGrid() {
        this.firmsGrid = new GridCrud<>(Firm.class);
        Iterable<Firm> iterable = firmsRepo.findAll();
        List<Firm> list = StreamSupport.stream(iterable.spliterator(), true).collect(Collectors.toList());
        this.firmsGrid.getGrid().setItems(list);
firmsGrid.setAddOperation(null);
        
    }

}
