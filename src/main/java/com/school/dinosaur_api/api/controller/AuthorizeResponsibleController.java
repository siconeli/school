package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.service.AuthorizeResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/responsibles/{responsibleId}")
@RestController
public class AuthorizeResponsibleController {

    private final AuthorizeResponsibleService authorizeResponsibleService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/authorize")
    public void authorize(@PathVariable Long responsibleId) {
        authorizeResponsibleService.authorize(responsibleId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/disauthorize")
    public void disauthorize(@PathVariable Long responsibleId) {
        authorizeResponsibleService.disauthorize(responsibleId);
    }
}
