package com.cakir.templateManagement.dto.mapper;

import com.cakir.templateManagement.dto.request.TemplateCreateDTO;
import com.cakir.templateManagement.dto.request.TemplateUpdateDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.entity.TemplateEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TemplateEntityMapper {

    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    public List<TemplateResponseDTO> toResponseDTOs(List<TemplateEntity> templateEntities) {
        return templateEntities.stream().map(this::toResponseDTO).toList();
    }

    public TemplateEntity toEntity(TemplateUpdateDTO templateUpdateDTO) {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setId(templateUpdateDTO.getId());
        templateEntity.setContent(templateUpdateDTO.getContent());
        userRepository.findById(templateUpdateDTO.getUserId()).ifPresent(templateEntity::setUser);

        return templateEntity;
    }

    public TemplateEntity toEntity(TemplateCreateDTO templateCreateDTO) {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setContent(templateCreateDTO.getContent());
        userRepository.findById(templateCreateDTO.getUserId()).ifPresent(templateEntity::setUser);

        return templateEntity;
    }

    public TemplateResponseDTO toResponseDTO(TemplateEntity templateEntity) {
        TemplateResponseDTO templateResponseDTO = new TemplateResponseDTO();
        templateResponseDTO.setId(templateEntity.getId() == null ? null : templateEntity.getId());
        templateResponseDTO.setContent(templateEntity.getContent());
        templateResponseDTO.setUserId(templateEntity.getUser().getId());

        return templateResponseDTO;
    }


}
