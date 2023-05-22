package uz.uat.mro.apps.model.ppcd.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.ppcd.entity.ImportedCard;
import uz.uat.mro.apps.model.ppcd.repository.ImportedCardsRepository;

@Service
@AllArgsConstructor
public class ImportedCardsService {
    private ImportedCardsRepository repo;

    public ImportedCard save(ImportedCard card) {
        return repo.save(card);
    }

    public void saveAll(List<ImportedCard> cards) {
        repo.saveAll(cards);
    }

    public void delete(ImportedCard card) {
        repo.delete(card);
    }

    public List<ImportedCard> findAllByRevision(Revision revision) {
        ImportedCard card = new ImportedCard();
        card.setRevision(revision);
        return StreamSupport.stream(repo.findAll(Example.of(card)).spliterator(), false).toList();
    }

}
