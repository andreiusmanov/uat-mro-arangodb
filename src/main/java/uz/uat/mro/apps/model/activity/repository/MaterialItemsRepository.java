package uz.uat.mro.apps.model.activity.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.MaterialItem;

public interface MaterialItemsRepository extends ArangoRepository<MaterialItem, String> {

    @Query(value = "for i in material_items filter i.project == @project return i")
    public Iterable<MaterialItem> findAllByProject(@Param("project") String project);
}
