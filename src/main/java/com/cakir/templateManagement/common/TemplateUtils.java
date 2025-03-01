package com.cakir.templateManagement.common;

import com.cakir.templateManagement.common.dto.TemplateVariableDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Dictionary;
import java.util.List;

@Service
@AllArgsConstructor
public class TemplateUtils {
    private final TemplateEngine templateEngine;

    public String generateTemplateBase64(TemplateVariableDTO templateVariableDTO) {
        Context context = new Context();

        String templateContent = templateVariableDTO.getTemplateContent();

        List<VariableMappingResponseDTO> variables = templateVariableDTO.getVariables();

        for (var variable : variables) {
            context.setVariable(variable.getTemplateVariable(), variable.getDefaultValue());
        }

        String renderedHtml = templateEngine.process(templateContent, context);
        return Base64.getEncoder().encodeToString(renderedHtml.getBytes(StandardCharsets.UTF_8));
    }

    public String generatePdfTemplateBase64(TemplateVariableDTO templateVariableDTO) {
        Context context = new Context();

        String templateContent = templateVariableDTO.getTemplateContent();

        List<VariableMappingResponseDTO> variables = templateVariableDTO.getVariables();

        for (var variable : variables) {
            context.setVariable(variable.getTemplateVariable(), variable.getDefaultValue());
        }

        String renderedHtml = templateEngine.process(templateContent, context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(renderedHtml);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error while generating PDF", e);
        }

        return Base64.getEncoder().encodeToString(outputStream.toByteArray());

    }

}
