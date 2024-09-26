package com.school.dinosaur_api.api.assembler;

import com.school.dinosaur_api.api.representationmodel.ResponsibleRepresentationModel;
import com.school.dinosaur_api.domain.model.Responsible;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ResponsibleAssembler {

    private ModelMapper modelMapper;

    public ResponsibleRepresentationModel toRepresentationModel(Responsible responsible) {
        return modelMapper.map(responsible, ResponsibleRepresentationModel.class);
    }

    public List<ResponsibleRepresentationModel> toCollectionRepresentationModel(List<Responsible> responsibleList) {
        return responsibleList
                .stream()
                .map(this::toRepresentationModel)
                .toList();
    }
}
