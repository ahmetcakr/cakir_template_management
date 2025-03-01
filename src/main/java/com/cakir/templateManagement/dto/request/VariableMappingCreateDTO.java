package com.cakir.templateManagement.dto.request;

import lombok.Data;

@Data
public class VariableMappingCreateDTO {
    private String templateVariable;
    private String defaultValue;
    private Long templateId;
}
