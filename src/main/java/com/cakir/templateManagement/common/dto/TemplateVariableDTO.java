package com.cakir.templateManagement.common.dto;

import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.DatasetEntity;
import lombok.Data;

import java.util.List;

@Data
public class TemplateVariableDTO {
    private String templateContent;
    private List<VariableResponseDTO> variables;
    private DatasetEntity datasetEntity;
}
