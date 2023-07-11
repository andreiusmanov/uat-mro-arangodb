package uz.uat.mro.apps.model.ppcd.entity;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class CardStatus {

    public static List<String> cardStatuses() {
        return ImmutableList.of("VALID", "VOID", "AMMENDED");
    }
}
