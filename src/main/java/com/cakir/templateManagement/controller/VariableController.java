package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/variables")
@RequiredArgsConstructor
public class VariableController {

    private final VariableService variableService;

    @GetMapping("/getAll")
    public ResponseEntity<List<VariableResponseDTO>> getAll(){
        return ResponseEntity.ok(variableService.getAllVariables());
    }

    @GetMapping("/getByTemplateId/{templateId}")
    public ResponseEntity<List<VariableResponseDTO>> getByTemplateId(@PathVariable Long templateId){
        return ResponseEntity.ok(variableService.getVariablesByTemplateId(templateId));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VariableResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(variableService.getVariableById(id).orElse(null));
    }

    @PostMapping("/save")
    public ResponseEntity<VariableResponseDTO> save(@RequestBody VariableUpdateDTO variableUpdateDTO){
        return ResponseEntity.ok(variableService.saveVariable(variableUpdateDTO));
    }
}
