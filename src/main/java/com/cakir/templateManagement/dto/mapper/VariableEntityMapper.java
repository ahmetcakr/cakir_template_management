package com.cakir.templateManagement.dto.mapper;

import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.VariableEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VariableEntityMapper {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;


    public List<VariableResponseDTO> entityToResponseDTOs(List<VariableEntity> variableMappingEntities) {
        return variableMappingEntities.stream().map(this::toResponseDTO).toList();
    }

    public List<VariableResponseDTO> updateDtoToResponseDTOs(List<VariableUpdateDTO> variableUpdateDTOS) {
        return variableUpdateDTOS.stream().map(this::toResponseDTO).toList();
    }

    public List<VariableEntity> toEntities(List<VariableUpdateDTO> variableUpdateDTOs) {
        return variableUpdateDTOs.stream().map(this::toEntity).toList();
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

    public VariableResponseDTO toResponseDTO(VariableUpdateDTO variableUpdateDTO) {
        VariableResponseDTO variableResponseDTO = new VariableResponseDTO();
        variableResponseDTO.setId(variableUpdateDTO.getId());
        variableResponseDTO.setDefaultValue(variableUpdateDTO.getDefaultValue());
        variableResponseDTO.setTemplateVariable(variableUpdateDTO.getTemplateVariable());
        variableResponseDTO.setTemplateId(variableUpdateDTO.getTemplateId());

        return variableResponseDTO;
    }
}
