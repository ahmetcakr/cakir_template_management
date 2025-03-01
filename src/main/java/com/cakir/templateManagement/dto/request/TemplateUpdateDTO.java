package com.cakir.templateManagement.dto.request;

import com.cakir.templateManagement.enums.TemplateVariableType;
import lombok.Data;

@Data
public class TemplateUpdateDTO {
    private Long id;
    private String content;
    private Long userId;
    private TemplateVariableType variableType;
}
