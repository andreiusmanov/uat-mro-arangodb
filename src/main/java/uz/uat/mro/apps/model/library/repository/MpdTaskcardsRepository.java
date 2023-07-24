package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdTaskcard;


public interface MpdTaskcardsRepository extends ArangoRepository<MpdTaskcard,String>{
    
@Query(value = "for i in mpd_taskcards filter i.edition ==@edition return i")    
public List<MpdTaskcard> findCardsByEdition(@Param("edition") String edition);



}
