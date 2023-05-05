package uz.uat.mro.apps.views.marketing.views;

import org.vaadin.crudui.layout.impl.VerticalCrudLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.marketing.layouts.MarketingLayout;

@PageTitle(value = "Маркетинг")
@Route(value = "marketing", layout = MarketingLayout.class)
public class MarketingView extends VerticalCrudLayout {



    
    public MarketingView() {

    }




}
