package com.cakir.templateManagement.dto.response;

import lombok.Data;

@Data
public class VariableResponseDTO {
    private Long id;
    private String templateVariable;
    private String defaultValue;
    private Long templateId;
}
