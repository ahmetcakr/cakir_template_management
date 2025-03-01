package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.dto.request.TemplateUpdateDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TemplateResponseDTO>> getAll(){
        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<TemplateResponseDTO>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(templateService.getTemplatesByUserId(userId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TemplateResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(templateService.getTemplateById(id).orElse(null));
    }

    @PostMapping("/save")
    public ResponseEntity<TemplateResponseDTO> save(@RequestBody TemplateUpdateDTO templateUpdateDTO){
        return ResponseEntity.ok(templateService.saveTemplate(templateUpdateDTO));
    }

}
