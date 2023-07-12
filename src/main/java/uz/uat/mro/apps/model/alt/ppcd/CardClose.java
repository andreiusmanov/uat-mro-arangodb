package uz.uat.mro.apps.model.alt.ppcd;

import java.time.LocalDate;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("cards_open")

public class CardClose {
    private String arangoId;
    private LocalDate date;
    private String employee;
}
