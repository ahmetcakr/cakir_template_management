package com.cakir.templateManagement.dto.request;

import lombok.Data;

@Data
public class VariableUpdateDTO {
    private Long id;
    private String templateVariable;
    private String defaultValue;
    private Long templateId;
}
