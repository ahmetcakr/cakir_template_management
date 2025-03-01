package com.cakir.templateManagement.service;

import com.cakir.templateManagement.common.dto.DocumentDTO;

public interface DocumentService {
    DocumentDTO generateDocument(Long templateId);

    DocumentDTO generatePdfDocument(Long templateId);
}
