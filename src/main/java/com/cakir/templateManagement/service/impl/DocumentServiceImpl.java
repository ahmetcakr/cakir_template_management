package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.common.TemplateUtils;
import com.cakir.templateManagement.common.dto.DocumentDTO;
import com.cakir.templateManagement.common.dto.TemplateVariableDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import com.cakir.templateManagement.service.DocumentService;
import com.cakir.templateManagement.service.TemplateService;
import com.cakir.templateManagement.service.VariableMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final TemplateService templateService;
    private final VariableMappingService variableMappingService;
    private final TemplateUtils templateUtils;

    @Override
    public DocumentDTO generateDocument(Long templateId) {
        DocumentDTO documentDTO = new DocumentDTO();

        TemplateVariableDTO templateVariableDTO = new TemplateVariableDTO();

        TemplateResponseDTO template = templateService.getTemplateById(templateId).orElse(null);

        if (template == null) {
            return null;
        }

        templateVariableDTO.setTemplateContent(template.getContent());

        List<VariableMappingResponseDTO> variablesByTemplateId = variableMappingService.getVariablesByTemplateId(template.getId());

        templateVariableDTO.setVariables(variablesByTemplateId);

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

        List<VariableMappingResponseDTO> variablesByTemplateId = variableMappingService.getVariablesByTemplateId(template.getId());

        templateVariableDTO.setVariables(variablesByTemplateId);

        documentDTO.setName("Document_" + LocalDateTime.now());
        documentDTO.setType("pdf");
        documentDTO.setBase64(templateUtils.generatePdfTemplateBase64(templateVariableDTO));

        return documentDTO;
    }
}
