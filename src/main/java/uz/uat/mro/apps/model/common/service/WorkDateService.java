package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.WorkDate;
import uz.uat.mro.apps.model.common.repository.WorkDateRepository;

@AllArgsConstructor
@Service
public class WorkDateService {
    private WorkDateRepository repo;

    public List<WorkDate> saveAll(List<WorkDate> dates) {
        return StreamSupport.stream(repo.saveAll(dates).spliterator(), false).toList();
    }

}
