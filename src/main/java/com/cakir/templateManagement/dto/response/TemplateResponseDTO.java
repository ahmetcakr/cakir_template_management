package com.cakir.templateManagement.dto.response;

import com.cakir.templateManagement.enums.TemplateVariableType;
import lombok.Data;

@Data
public class TemplateResponseDTO {
    private Long id;
    private String content;
    private Long userId;
    private TemplateVariableType variableType;
}
