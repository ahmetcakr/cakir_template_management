package com.cakir.templateManagement.dto.request;

import lombok.Data;

@Data
public class VariableMappingUpdateDTO {
    private Long id;
    private String templateVariable;
    private String defaultValue;
    private Long templateId;
}
