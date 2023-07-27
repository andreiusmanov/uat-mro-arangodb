package uz.uat.mro.apps.model.library.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.library.repository.MpdItemsRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdTaskcardsRepo;

@AllArgsConstructor
@Service
public class MpdService {

    private MpdItemsRepo itemsRepo;
    private MpdTaskcardsRepo cardsRepo;

    public MpdItem save(MpdItem item) {
        return itemsRepo.save(item);
    }

    public void delete(MpdItem item) {
        itemsRepo.delete(item);
    }

    public MpdItem findOneByNumberAndEdition(String number, MpdEdition edition) {
        MpdItem item = new MpdItem();
        item.setNumber(number);
        item.setEdition(edition);
        return itemsRepo.findOne(Example.of(item)).get();
    }

    // public List<MpdItem> findByNumberAndModel(String number, String model) {
    // return null; // itemsRepo.findByNumberAndModel(number, model);
    // }

    public List<MpdItem> findItemsByEdition(String edition) {
        return itemsRepo.findByEdition(edition);
    }

    public List<MpdTaskcard> findCardsByEdition(String edition) {
        return cardsRepo.findCardsByEdition(edition);
    }

}
