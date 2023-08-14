package uz.uat.mro.apps.views.marketing.views;

import java.util.List;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.apps.model.alt.common.Maintenance;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.utils.MyUtils;

public class MComposite extends AbstractCompositeField<Div, MComposite, List<Maintenance>> {
    private List<Maintenance> service;
    private List<Maintenance> maintenances;
    private TextField textField;
    private Project project;

    public MComposite(List<Maintenance> defaultValue) {

        super(defaultValue);
        this.service = defaultValue;
        this.textField = new TextField();
        this.project = (Project) MyUtils.getAttribute("project");

        this.addValueChangeListener(e -> {
            this.textField.setValue(makePresentation(e.getValue()));
        });
    }

    @Override
    protected void setPresentationValue(List<Maintenance> newPresentationValue) {
        this.textField.setValue(makePresentation(newPresentationValue));
    }

    private String makePresentation(List<Maintenance> maintenances) {
        StringBuilder builder = new StringBuilder();
        for (Maintenance maintenance : maintenances) {
            builder.append(maintenance.getIndex() + maintenance.getCode() + ",");
        }
        String result = builder.toString().replaceFirst(",(?!.*,)", "");
        return result;
    }

}
