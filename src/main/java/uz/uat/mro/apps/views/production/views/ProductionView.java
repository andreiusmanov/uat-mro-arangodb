package uz.uat.mro.apps.views.production.views;

import org.vaadin.crudui.layout.impl.VerticalCrudLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.marketing.layouts.MarketingLayout;

@PageTitle(value = "Производство")
@Route(value = "production", layout = MarketingLayout.class)
public class ProductionView extends VerticalCrudLayout {



    
    public ProductionView() {

    }




}
