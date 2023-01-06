package uz.uat.mro.apps.views.common.views;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.service.CountryService;
import uz.uat.mro.apps.views.common.layouts.CommonLayout;

@PageTitle(value = "Страны")
@Route(value = "common/countries", layout = CommonLayout.class)
@RouteAlias(value = "", layout = CommonLayout.class)
public class CountriesView extends VerticalLayout {

    private CountryService service;
    private GridCrud<Country> grid;

    /**
     * 
     */
    public CountriesView(CountryService service) {
        this.service = service;
        countriesGrid();
        add(new H3("Страны"), grid);
    }

    private void countriesGrid() {
        this.grid = new GridCrud<>(Country.class);
        List<Country> list = service.findAll();
        this.grid.getGrid().setItems(list);
        grid.getGrid().setColumns("shortName", "id", "code3", "numeric", "name");
        grid.getGrid().getColumnByKey("shortName").setHeader("Кратк. название");
        grid.getGrid().getColumnByKey("id").setHeader("Код2");
        grid.getGrid().getColumnByKey("code3").setHeader("Код3");
        grid.getGrid().getColumnByKey("numeric").setHeader("Цифр. код");
        grid.getGrid().getColumnByKey("name").setHeader("Наименование");

        CrudFormFactory<Country> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("shortName", "id", "code3", "numeric", "name");
        factory.setFieldCaptions("Кратк. название", "Код2", "Код3", "Цифр. код", "Наименование");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());

    }
}
