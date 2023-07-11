package uz.uat.mro.apps.utils;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class RevisionStatuses {
    public static List<String> statuses(){
        return ImmutableList.of("Registered", "Pending");
    }
}
