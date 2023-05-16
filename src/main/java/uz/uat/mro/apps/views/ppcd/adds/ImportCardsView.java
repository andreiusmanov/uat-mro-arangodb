package uz.uat.mro.apps.views.ppcd.adds;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.apps.model.activity.entity.Revision;

public class ImportCardsView extends FormLayout {

    private TextField number;
    private DatePicker date;
    private TextArea description;
    private TextField fileUrl;
    private Button button;
    private Span fileUrlSpan;
    private Binder<Revision> binder;

    private Revision revision;

    public ImportCardsView() {
        span();
        binder();
        add(fileUrlSpan);
    }

    private void binder() {
        this.binder = new Binder<>(Revision.class);
        binder.bind(number, e -> e.getNumber(), (e, number) -> e.setNumber(number));
        binder.bind(date, e -> e.getDate(), (e, date) -> e.setDate(date));
        binder.bind(description, e -> e.getDescription(), (e, description) -> e.setDescription(description));
        binder.setBean(revision);
    }

    private void span() {
        this.fileUrlSpan = new Span();
        this.fileUrl = new TextField();
        this.fileUrl.setPlaceholder("Путь к файлу");
        this.button = new Button(VaadinIcon.BROWSER.create());
        fileUrlSpan.add(fileUrl, new Div(), button);
    }

    private void data() {

    }
}
