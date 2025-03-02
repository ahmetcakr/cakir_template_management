package com.cakir.templateManagement.service;

import com.cakir.templateManagement.common.dto.DocumentDTO;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;

import java.util.List;

public interface DocumentService {
    DocumentDTO generateDocument(Long templateId);

    DocumentDTO generatePdfDocument(Long templateId);

    DocumentDTO generatePdfDocumentByForm(Long templateId, List<VariableUpdateDTO> variables);
}
