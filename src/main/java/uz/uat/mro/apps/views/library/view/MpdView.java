package uz.uat.mro.apps.views.library.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.utils.Keys;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.MpdLayout;

@PageTitle(value = "MPD")
@Route(value = "mpd/edition", layout = MpdLayout.class)
public class MpdView extends VerticalLayout {
    private FormLayout form;
    private MpdEdition edition;

    public MpdView() {
        this.edition = (MpdEdition) MyUtils.getAttribute(Keys.MPD_EDITION);
        form();
        add(form);
    }

    private void form() {
        this.form = new FormLayout();
        Binder<MpdEdition> binder = new Binder<>(MpdEdition.class);
        TextField date = new TextField("Дата издания");
        TextField number = new TextField("Номер");
        TextField message = new TextField("Версия");
        TextField model = new TextField("Модель ВС");
        form.add(date, number, message, model);
        binder.forField(date).bindReadOnly(e -> e.getDate().toString());
        binder.forField(number).bindReadOnly(e -> e.getNumber());
        binder.forField(message).bindReadOnly(e -> e.getMessage());
        binder.forField(model).bindReadOnly(e -> e.getModel().getName());
        binder.setBean(edition);
        binder.readBean(edition);
    }

}
