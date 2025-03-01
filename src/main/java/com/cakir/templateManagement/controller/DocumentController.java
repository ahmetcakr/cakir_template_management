package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.common.dto.DocumentDTO;
import com.cakir.templateManagement.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;

@RestController
@RequestMapping("api/v1/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/generate")
    public DocumentDTO generateDocument(@RequestParam Long templateId){
        return documentService.generateDocument(templateId);
    }

    @PostMapping("/generatePdf")
    public DocumentDTO generatePdf(@RequestParam Long templateId){
        return documentService.generatePdfDocument(templateId);
    }
}
