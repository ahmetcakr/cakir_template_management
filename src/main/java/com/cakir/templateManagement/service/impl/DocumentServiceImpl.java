package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.common.utils.TemplateUtils;
import com.cakir.templateManagement.common.dto.DocumentDTO;
import com.cakir.templateManagement.common.dto.TemplateVariableDTO;
import com.cakir.templateManagement.dto.mapper.DatasetEntityMapper;
import com.cakir.templateManagement.dto.response.DatasetResponseDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.enums.TemplateVariableType;
import com.cakir.templateManagement.service.DatasetService;
import com.cakir.templateManagement.service.DocumentService;
import com.cakir.templateManagement.service.TemplateService;
import com.cakir.templateManagement.service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final TemplateService templateService;
    private final VariableService variableService;
    private final TemplateUtils templateUtils;
    private final DatasetService datasetService;
    private final DatasetEntityMapper datasetEntityMapper;

    @Override
    public DocumentDTO generateDocument(Long templateId) {
        DocumentDTO documentDTO = new DocumentDTO();

        TemplateVariableDTO templateVariableDTO = new TemplateVariableDTO();

        TemplateResponseDTO template = templateService.getTemplateById(templateId).orElse(null);

        if (template == null) {
            return null;
        }

        templateVariableDTO.setTemplateContent(template.getContent());

        List<VariableResponseDTO> variablesByTemplateId = variableService.getVariablesByTemplateId(template.getId());

        templateVariableDTO.setVariables(variablesByTemplateId);

        if (template.getVariableType() == TemplateVariableType.URL){
            DatasetResponseDTO resp = datasetService.getDatasetByTemplateId(templateId).orElse(null);

            if (resp != null) {
                templateVariableDTO.setDatasetEntity(datasetEntityMapper.toEntity(resp));
            }
        }

        documentDTO.setName("Document_" + LocalDateTime.now());
        documentDTO.setType("html");
        documentDTO.setBase64(templateUtils.generateTemplateBase64(templateVariableDTO));

        return documentDTO;

    }

    @Override
    public DocumentDTO generatePdfDocument(Long templateId) {
        DocumentDTO documentDTO = new DocumentDTO();

        TemplateVariableDTO templateVariableDTO = new TemplateVariableDTO();

        TemplateResponseDTO template = templateService.getTemplateById(templateId).orElse(null);

        if (template == null) {
            return null;
        }

        templateVariableDTO.setTemplateContent(template.getContent());

        List<VariableResponseDTO> variablesByTemplateId = variableService.getVariablesByTemplateId(template.getId());

        templateVariableDTO.setVariables(variablesByTemplateId);

        if (template.getVariableType() == TemplateVariableType.URL){
            DatasetResponseDTO resp = datasetService.getDatasetByTemplateId(templateId).orElse(null);

            if (resp != null) {
                templateVariableDTO.setDatasetEntity(datasetEntityMapper.toEntity(resp));
            }
        }

        documentDTO.setName("Document_" + LocalDateTime.now());
        documentDTO.setType("pdf");
        documentDTO.setBase64(templateUtils.generatePdfTemplateBase64(templateVariableDTO));

        return documentDTO;
    }
}
