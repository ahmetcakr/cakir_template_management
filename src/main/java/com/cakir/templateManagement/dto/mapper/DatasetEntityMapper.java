package com.cakir.templateManagement.dto.mapper;

import com.cakir.templateManagement.dto.request.DatasetUpdateDTO;
import com.cakir.templateManagement.dto.request.TemplateUpdateDTO;
import com.cakir.templateManagement.dto.response.DatasetResponseDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.entity.DatasetEntity;
import com.cakir.templateManagement.entity.TemplateEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DatasetEntityMapper {

    private final TemplateRepository templateRepository;

    public List<DatasetResponseDTO> toResponseDTOs(List<DatasetEntity> datasetEntities) {
        return datasetEntities.stream().map(this::toResponseDTO).toList();
    }

    public DatasetEntity toEntity(DatasetUpdateDTO datasetUpdateDTO) {

        if (datasetUpdateDTO == null) {
            return null;
        }

        DatasetEntity datasetEntity = new DatasetEntity();
        datasetEntity.setId(datasetUpdateDTO.getId());
        datasetEntity.setName(datasetUpdateDTO.getName());
        datasetEntity.setApiUrl(datasetUpdateDTO.getApiUrl());
        datasetEntity.setApiMethod(datasetUpdateDTO.getApiMethod());
        datasetEntity.setAuthRequired(datasetUpdateDTO.isAuthRequired());
        datasetEntity.setApiKey(datasetUpdateDTO.getApiKey());
        datasetEntity.setApiAuthorizationType(datasetUpdateDTO.getApiAuthorizationType());
        datasetEntity.setResponseBody(datasetUpdateDTO.getResponseBody());
        datasetEntity.setTemplate(templateRepository.findById(datasetUpdateDTO.getTemplateId()).orElse(null));

        return datasetEntity;
    }

    public DatasetEntity toEntity(DatasetResponseDTO datasetUpdateDTO) {

        if (datasetUpdateDTO == null) {
            return null;
        }

        DatasetEntity datasetEntity = new DatasetEntity();
        datasetEntity.setId(datasetUpdateDTO.getId());
        datasetEntity.setName(datasetUpdateDTO.getName());
        datasetEntity.setApiUrl(datasetUpdateDTO.getApiUrl());
        datasetEntity.setApiMethod(datasetUpdateDTO.getApiMethod());
        datasetEntity.setAuthRequired(datasetUpdateDTO.isAuthRequired());
        datasetEntity.setApiKey(datasetUpdateDTO.getApiKey());
        datasetEntity.setApiAuthorizationType(datasetUpdateDTO.getApiAuthorizationType());
        datasetEntity.setResponseBody(datasetUpdateDTO.getResponseBody());
        datasetEntity.setTemplate(templateRepository.findById(datasetUpdateDTO.getTemplateId()).orElse(null));

        return datasetEntity;
    }


    public DatasetResponseDTO toResponseDTO(DatasetEntity datasetEntity) {

        if (datasetEntity == null) {
            return null;
        }

        DatasetResponseDTO datasetResponseDTO = new DatasetResponseDTO();
        datasetResponseDTO.setId(datasetEntity.getId());
        datasetResponseDTO.setName(datasetEntity.getName());
        datasetResponseDTO.setTemplateId(datasetEntity.getTemplate().getId());
        datasetResponseDTO.setApiUrl(datasetEntity.getApiUrl());
        datasetResponseDTO.setApiMethod(datasetEntity.getApiMethod());
        datasetResponseDTO.setAuthRequired(datasetEntity.isAuthRequired());
        datasetResponseDTO.setApiKey(datasetEntity.getApiKey());
        datasetResponseDTO.setApiAuthorizationType(datasetEntity.getApiAuthorizationType());
        datasetResponseDTO.setResponseBody(datasetEntity.getResponseBody());

        return datasetResponseDTO;
    }
}
