package uz.uat.mro.apps.views.logistics.views;

import org.vaadin.crudui.layout.impl.VerticalCrudLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.marketing.layouts.MarketingLayout;

@PageTitle(value = "Отдел Комплектации")
@Route(value = "logistics", layout = MarketingLayout.class)
public class LogisticsView extends VerticalCrudLayout {



    
    public LogisticsView() {

    }




}
