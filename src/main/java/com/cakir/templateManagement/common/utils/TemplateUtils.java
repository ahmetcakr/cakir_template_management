package com.cakir.templateManagement.common.utils;

import com.cakir.templateManagement.common.dto.TemplateVariableDTO;
import com.cakir.templateManagement.common.service.HttpService;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.DatasetEntity;
import com.cakir.templateManagement.enums.ApiMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TemplateUtils {
    private final TemplateEngine templateEngine;
    private final HttpService httpService;

    public String generateTemplateBase64(TemplateVariableDTO templateVariableDTO) {
        Context context = new Context();
        String templateContent = templateVariableDTO.getTemplateContent();

        if (templateVariableDTO.getDatasetEntity() == null) {
            List<VariableResponseDTO> variables = templateVariableDTO.getVariables();
            for (var variable : variables) {
                context.setVariable(variable.getTemplateVariable(), variable.getDefaultValue());
            }
        } else {
            fillContextWithApiData(context, templateVariableDTO);
        }

        String renderedHtml = templateEngine.process(templateContent, context);
        return Base64.getEncoder().encodeToString(renderedHtml.getBytes(StandardCharsets.UTF_8));
    }


    public String generatePdfTemplateBase64(TemplateVariableDTO templateVariableDTO) {
        Context context = new Context();
        String templateContent = templateVariableDTO.getTemplateContent();

        if (templateVariableDTO.getDatasetEntity() == null) {
            List<VariableResponseDTO> variables = templateVariableDTO.getVariables();
            for (var variable : variables) {
                context.setVariable(variable.getTemplateVariable(), variable.getDefaultValue());
            }
        } else {
            fillContextWithApiData(context, templateVariableDTO);
        }

        String renderedHtml = templateEngine.process(templateContent, context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(renderedHtml);
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }

        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    private void fillContextWithApiData(Context context, TemplateVariableDTO templateVariableDTO) {
        DatasetEntity datasetEntity = templateVariableDTO.getDatasetEntity();
        ApiMethod apiMethod = datasetEntity.getApiMethod();

        Map<String, Object> stringObjectMap = switch (apiMethod) {
            case GET -> httpService.sendGetRequest(
                    datasetEntity.getApiUrl(),
                    datasetEntity.getApiKey(),
                    datasetEntity.getApiAuthorizationType().toString());
            case POST -> httpService.sendPostRequest(
                    datasetEntity.getApiUrl(),
                    "",
                    datasetEntity.getApiKey(),
                    datasetEntity.getApiAuthorizationType().toString());
            default -> null;
        };

        List<VariableResponseDTO> variables = templateVariableDTO.getVariables();
        for (var variable : variables) {
            if (stringObjectMap == null) {
                context.setVariable(variable.getTemplateVariable(), variable.getDefaultValue());
                continue;
            }
            String value = (String) stringObjectMap.getOrDefault(variable.getTemplateVariable(), variable.getDefaultValue());
            context.setVariable(variable.getTemplateVariable(), value);
        }
    }

}
