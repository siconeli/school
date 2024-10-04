package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.model.Responsible;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AuthorizeResponsibleService {

    private final ResponsibleService responsibleService;

    @Transactional
    public void authorize(Long responsibleId) {
        Responsible responsible = responsibleService.findResponsible(responsibleId);
        responsible.authorizeResponsible();
    }

    @Transactional
    public void disauthorize(Long responsibleId) {
        Responsible responsible = responsibleService.findResponsible(responsibleId);
        responsible.disauthorizeResponsible();
    }
}
