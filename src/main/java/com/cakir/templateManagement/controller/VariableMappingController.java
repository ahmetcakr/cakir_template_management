package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.dto.request.VariableMappingUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import com.cakir.templateManagement.service.VariableMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/variable-mapping")
@RequiredArgsConstructor
public class VariableMappingController {

    private final VariableMappingService variableMappingService;

    @GetMapping("/getAll")
    public ResponseEntity<List<VariableMappingResponseDTO>> getAll(){
        return ResponseEntity.ok(variableMappingService.getAllVariables());
    }

    @GetMapping("/getByTemplateId/{templateId}")
    public ResponseEntity<List<VariableMappingResponseDTO>> getByTemplateId(@PathVariable Long templateId){
        return ResponseEntity.ok(variableMappingService.getVariablesByTemplateId(templateId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VariableMappingResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(variableMappingService.getVariableById(id).orElse(null));
    }

    @PostMapping("/save")
    public ResponseEntity<VariableMappingResponseDTO> save(@RequestBody VariableMappingUpdateDTO variableMappingUpdateDTO){
        return ResponseEntity.ok(variableMappingService.saveVariable(variableMappingUpdateDTO));
    }
}
