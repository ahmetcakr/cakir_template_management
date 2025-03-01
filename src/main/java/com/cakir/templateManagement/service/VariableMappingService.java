package com.cakir.templateManagement.service;

import com.cakir.templateManagement.dto.request.VariableMappingUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;

import java.util.List;
import java.util.Optional;

public interface VariableMappingService {
    List<VariableMappingResponseDTO> getAllVariables();
    List<VariableMappingResponseDTO> getVariablesByTemplateId(Long templateId);
    Optional<VariableMappingResponseDTO> getVariableById(Long id);
    VariableMappingResponseDTO saveVariable(VariableMappingUpdateDTO variableMappingUpdateDTO);
}
