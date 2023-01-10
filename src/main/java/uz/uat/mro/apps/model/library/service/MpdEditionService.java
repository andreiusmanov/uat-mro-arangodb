package uz.uat.mro.apps.model.library.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.library.repository.MpdEditionsRepository;

@AllArgsConstructor
@Service
public class MpdEditionService {
    private MpdEditionsRepository repo;
}
