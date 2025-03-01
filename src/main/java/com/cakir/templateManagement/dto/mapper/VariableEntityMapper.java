package com.cakir.templateManagement.dto.mapper;

import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.VariableEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VariableEntityMapper {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    public List<VariableResponseDTO> toResponseDTOs(List<VariableEntity> variableMappingEntities) {
        return variableMappingEntities.stream().map(this::toResponseDTO).toList();
    }

    public VariableEntity toEntity(VariableUpdateDTO variableUpdateDTO) {
        VariableEntity variableEntity = new VariableEntity();

        variableEntity.setId(variableUpdateDTO.getId());
        variableEntity.setDefaultValue(variableUpdateDTO.getDefaultValue());
        variableEntity.setTemplateVariable(variableUpdateDTO.getTemplateVariable());

        templateRepository.findById(variableUpdateDTO.getTemplateId()).ifPresent(variableEntity::setTemplate);

        return variableEntity;
    }

    public VariableResponseDTO toResponseDTO(VariableEntity variableEntity) {
        VariableResponseDTO variableResponseDTO = new VariableResponseDTO();
        variableResponseDTO.setId(variableEntity.getId());
        variableResponseDTO.setDefaultValue(variableEntity.getDefaultValue());
        variableResponseDTO.setTemplateVariable(variableEntity.getTemplateVariable());
        variableResponseDTO.setTemplateId(variableEntity.getTemplate().getId());

        return variableResponseDTO;
    }
}
