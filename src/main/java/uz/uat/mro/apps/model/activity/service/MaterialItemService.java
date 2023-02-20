package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.MaterialItem;
import uz.uat.mro.apps.model.activity.repository.MaterialItemsRepository;

@AllArgsConstructor
@Service
public class MaterialItemService {
    private MaterialItemsRepository repo;

    public List<MaterialItem> findAllByProject(String project) {
        return StreamSupport.stream(repo.findAllByProject(project).spliterator(), false).toList();
    }
}
