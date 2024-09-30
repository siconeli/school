package com.school.dinosaur_api.configuration;

import com.school.dinosaur_api.api.representationmodel.output.ResponsibleOutput;
import com.school.dinosaur_api.domain.model.Responsible;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Biblioteca de terceiros, é necessário configurar o Bean para que seja possível fazer a injeção de dependências em outra classe.
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Responsible.class, ResponsibleOutput.class)
                .addMappings(mapper -> mapper.map(Responsible::getTelephone, ResponsibleOutput::setTel));

        return modelMapper;
    }
}
