package uz.uat.mro.apps.views.library.view;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.service.MpdEditionService;
import uz.uat.mro.apps.utils.MyUtils;
import uz.uat.mro.apps.views.library.layout.LibraryLayout;

@PageTitle(value = "MPD издания")
@Route(value = "library/mpd-editions", layout = LibraryLayout.class)
public class MpdEditionsView extends VerticalLayout {
    private MpdEditionService service;
    private GridCrud<MpdEdition> grid;
    private Button details;

    private MpdEdition edition;

    public MpdEditionsView(MpdEditionService service) {
        this.service = service;
        grid();
        add(grid);
    }

    private void details() {
        this.details = new Button("Данные");
        this.details.setEnabled(false);
        this.details.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        details.addClickListener(e -> {
            MyUtils.setAttribute("mpd_edition", edition);
            UI.getCurrent().navigate("mpd-edition/edition");
        });
    }

    private void grid() {
        this.grid = new GridCrud<>(MpdEdition.class);
        grid.getGrid().setColumns("date", "number", "message", "model.name");
        grid.getGrid().getColumnByKey("date").setHeader("Дата издания");
        grid.getGrid().getColumnByKey("number").setHeader("Номер");
        grid.getGrid().getColumnByKey("message").setHeader("Версия");
        grid.getGrid().getColumnByKey("model.name").setHeader("Модель ВС");

        details();

        grid.getCrudLayout().addToolbarComponent(details);
        grid.getGrid().getSelectionModel().addSelectionListener(e -> {
            this.edition = grid.getGrid().getSelectionModel().getFirstSelectedItem().orElse(null);
            this.details.setEnabled(!grid.getGrid().getSelectedItems().isEmpty());
        });

        CrudFormFactory<MpdEdition> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("date", "number", "message", "model");
        factory.setFieldCaptions("Дата издания", "Номер", "Версия", "Модель ВС");

        factory.setFieldProvider("model", user -> {
            ComboBox<MajorModel> cb = new ComboBox<>("Модель ВС", service.findAllModels());
            cb.setItemLabelGenerator(e -> e.getName());
            return cb;
        });

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());
    }

}
