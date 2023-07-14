package uz.uat.mro.apps.views.common.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.common.layouts.AdminLayout;

@PageTitle("Общие данные")
@Route(value = "common", layout = AdminLayout.class)
public class CommonView extends VerticalLayout {
    private Anchor countries;
    private Anchor currencies;
    private Anchor stations;
    private Anchor organizations;

    public CommonView() {
        this.countries = new Anchor("common/countries", "Countries");
        this.currencies = new Anchor("common/currencies", "Currencies");
        this.stations = new Anchor("common/stations", "Stations");
        this.organizations = new Anchor("common/organizations", "Organizations");
        add(countries, currencies, stations, organizations);
    }

}
