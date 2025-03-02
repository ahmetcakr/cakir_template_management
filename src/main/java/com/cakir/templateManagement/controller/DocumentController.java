package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.common.dto.DocumentDTO;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Document;
import java.util.List;

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

    @PostMapping("/generatePdfByForm")
    public DocumentDTO generatePdfByForm(@RequestParam Long templateId, @RequestBody List<VariableUpdateDTO> updateDTOS){
        return documentService.generatePdfDocumentByForm(templateId, updateDTOS);
    }
}
