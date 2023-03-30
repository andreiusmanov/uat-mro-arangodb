package uz.uat.mro.apps.utils;

import java.time.LocalDate;

import uz.uat.mro.apps.model.common.entity.WorkDate;
import uz.uat.mro.apps.model.common.repository.WorkDateRepository;
import uz.uat.mro.apps.model.common.service.WorkDateService;

public class DateUtil {

    private WorkDateService service;

    /**
     * @param repo
     */
    public DateUtil(WorkDateService service) {
        this.service = service;
    }

public static void main(String[] args) {
int i = 800;
LocalDate start = LocalDate.of(2022, 9, 30);

for (int j = 0; j < i; j++) {
    WorkDate w = new WorkDate();
    w.setId(start.plusDays(j).toString());
}

}
    

}
