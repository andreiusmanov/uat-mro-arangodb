package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Currency;
import uz.uat.mro.apps.model.common.service.CurrencyService;
import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle(value = "Валюты")
@Route(value = "common/currencies", layout = AdminLayout.class)
public class CurrenciesView extends VerticalLayout {

    private CurrencyService service;
    private GridCrud<Currency> grid;

    /**
     * 
     */
    public CurrenciesView(CurrencyService service) {
        this.service = service;
        grid();
        add(new H3("Валюты"), grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Currency.class);
        this.grid.getGrid().setColumns("id", "numeric", "name", "countries");
        this.grid.getGrid().getColumnByKey("id").setHeader("Код");
        this.grid.getGrid().getColumnByKey("numeric").setHeader("Цифр. код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.grid.getGrid().getColumnByKey("countries").setHeader("Страны");

        CrudFormFactory<Currency> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("id", "numeric", "name");
        factory.setFieldCaptions("Код", "Цифр. код", "Наименование");

        // factory.setFieldProvider("countries", user -> {
        //     ComboBox<Country> countries = new ComboBox<>();
        //     countries.setItems(service.findCountries());
        //     countries.setItemLabelGenerator(e -> e.getShortName());
        //     return countries;
        // });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());
    }
}
