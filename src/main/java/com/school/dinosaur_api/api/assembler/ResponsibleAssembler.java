package com.school.dinosaur_api.api.assembler;

import com.school.dinosaur_api.api.representationmodel.input.ResponsibleInput;
import com.school.dinosaur_api.api.representationmodel.output.ResponsibleOutput;
import com.school.dinosaur_api.domain.model.Responsible;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ResponsibleAssembler {

    private ModelMapper modelMapper;

    public Responsible toEntity (ResponsibleInput responsibleInput) {
        return modelMapper.map(responsibleInput, Responsible.class);
    }

    public ResponsibleOutput toRepresentationModel (Responsible responsible) {
        return modelMapper.map(responsible, ResponsibleOutput.class);
    }

    public List<ResponsibleOutput> toCollectionRepresentationModel (List<Responsible> responsibleList) {
        return responsibleList
                .stream()
                .map(this::toRepresentationModel)
                .toList();
    }
}
