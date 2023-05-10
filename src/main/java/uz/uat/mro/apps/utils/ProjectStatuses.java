package uz.uat.mro.apps.utils;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class ProjectStatuses {
    public static List<String> projectStatuses() {
        return ImmutableList.of("Проект", "Активный", "Отложен", "Завершен", "Отменен");
    }
}
