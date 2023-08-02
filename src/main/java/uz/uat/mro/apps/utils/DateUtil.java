package uz.uat.mro.apps.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uz.uat.mro.apps.model.alt.common.WorkDate;
import uz.uat.mro.apps.model.services.common.CommonService;

public class DateUtil {

    private static CommonService service;

    /**
     * @param repo
     */
    public DateUtil(CommonService service) {
        this.service = service;
    }

    public static void main(String[] args) {

        final DateUtil util = new DateUtil(service);
        int i = 800;
        LocalDate start = LocalDate.of(2022, 9, 30);
        List<WorkDate> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            WorkDate w = new WorkDate();
            w.setId(start.plusDays(j).toString());
            w.setDate(start.plusDays(j).toString());
            list.add(w);
        }
        util.service.saveAll(list);

    }

}
