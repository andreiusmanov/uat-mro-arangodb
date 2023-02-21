package uz.uat.mro.apps.views.activity.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Import Working Package")
@Route(value = "project/working-package", layout = ProjectLayout.class)
public class ImportWorkingPackageView extends VerticalLayout {

}
