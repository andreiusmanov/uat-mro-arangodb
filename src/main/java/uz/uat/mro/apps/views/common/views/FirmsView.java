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
import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.service.FirmsService;

@PageTitle(value = "Организация")
@Route(value = "common/firms")
public class FirmsView extends VerticalLayout {
    private FirmsService service;
    private GridCrud<Firm> grid;

    public FirmsView(FirmsService service) {
        this.service = service;
        grid();
        add(new H3("Организации"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Firm.class);
        List<Firm> list = service.findFirms();
        this.grid.getGrid().setItems(list);

        CrudFormFactory<Firm> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("country", "code", "name", "shortName");
        factory.setFieldCaptions("Страна", "Код", "Наименование", "Аббревиатура");

        factory.setFieldProvider("country", user -> {
            ComboBox<Country> countries = new ComboBox<>();
            countries.setItems(service.findAllCountries());
            countries.setItemLabelGenerator(e -> e.getShortName());
            return countries;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(service::findFirms);
    }

}
