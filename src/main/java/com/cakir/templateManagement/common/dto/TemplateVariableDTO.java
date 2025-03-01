package com.cakir.templateManagement.common.dto;

import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import lombok.Data;

import java.util.Dictionary;
import java.util.List;

@Data
public class TemplateVariableDTO {
    private String templateContent;
    private List<VariableMappingResponseDTO> variables;
}
