package uz.uat.mro.apps.views.ppcd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.views.activity.layouts.ProjectLayout;

@PageTitle(value = "Загрузка списка рабочих карт")
@Route(value = "ppcd/revision/download", layout = ProjectLayout.class)
public class RevisionDownloadView extends VerticalLayout {

    private TextField url;
    private Button chooser;
    
    public RevisionDownloadView() {
        add(new H3("Загрузка данных LOV MC"));
    }

}
