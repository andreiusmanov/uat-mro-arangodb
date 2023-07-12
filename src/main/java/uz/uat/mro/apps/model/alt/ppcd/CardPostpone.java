package uz.uat.mro.apps.model.alt.ppcd;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CardPostpone {
    private String arangoId;
    private LocalDate date;
    private String employee;
}
