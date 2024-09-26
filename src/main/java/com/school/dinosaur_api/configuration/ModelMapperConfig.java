package com.school.dinosaur_api.configuration;

import com.school.dinosaur_api.api.representationmodel.ResponsibleRepresentationModel;
import com.school.dinosaur_api.domain.model.Responsible;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Responsible.class, ResponsibleRepresentationModel.class)
                .addMappings(mapper -> mapper.map(Responsible::getTelephone, ResponsibleRepresentationModel::setTel));

        return modelMapper;
    }
}
