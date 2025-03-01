package com.cakir.templateManagement.service;

import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;

import java.util.List;
import java.util.Optional;

public interface VariableService {
    List<VariableResponseDTO> getAllVariables();
    List<VariableResponseDTO> getVariablesByTemplateId(Long templateId);
    Optional<VariableResponseDTO> getVariableById(Long id);
    VariableResponseDTO saveVariable(VariableUpdateDTO variableUpdateDTO);
}
