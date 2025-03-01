package com.cakir.templateManagement.service;

import com.cakir.templateManagement.dto.request.TemplateUpdateDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TemplateService {
    List<TemplateResponseDTO> getAllTemplates();
    List<TemplateResponseDTO> getTemplatesByUserId(Long userId);
    Optional<TemplateResponseDTO> getTemplateById(Long id);
    TemplateResponseDTO saveTemplate(TemplateUpdateDTO templateUpdateDTO);
}
