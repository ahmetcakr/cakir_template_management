package com.cakir.templateManagement.dto.mapper;

import com.cakir.templateManagement.dto.request.VariableMappingUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import com.cakir.templateManagement.entity.VariableMappingEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VariableMappingEntityMapper {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    public List<VariableMappingResponseDTO> toResponseDTOs(List<VariableMappingEntity> variableMappingEntities) {
        return variableMappingEntities.stream().map(this::toResponseDTO).toList();
    }

    public VariableMappingEntity toEntity(VariableMappingUpdateDTO variableMappingUpdateDTO) {
        VariableMappingEntity variableMappingEntity = new VariableMappingEntity();

        variableMappingEntity.setId(variableMappingUpdateDTO.getId());
        variableMappingEntity.setDefaultValue(variableMappingUpdateDTO.getDefaultValue());
        variableMappingEntity.setTemplateVariable(variableMappingUpdateDTO.getTemplateVariable());

        templateRepository.findById(variableMappingUpdateDTO.getTemplateId()).ifPresent(variableMappingEntity::setTemplate);

        return variableMappingEntity;
    }

    public VariableMappingResponseDTO toResponseDTO(VariableMappingEntity variableMappingEntity) {
        VariableMappingResponseDTO variableMappingResponseDTO = new VariableMappingResponseDTO();
        variableMappingResponseDTO.setId(variableMappingEntity.getId());
        variableMappingResponseDTO.setDefaultValue(variableMappingEntity.getDefaultValue());
        variableMappingResponseDTO.setTemplateVariable(variableMappingEntity.getTemplateVariable());
        variableMappingResponseDTO.setTemplateId(variableMappingEntity.getTemplate().getId());

        return variableMappingResponseDTO;
    }
}
