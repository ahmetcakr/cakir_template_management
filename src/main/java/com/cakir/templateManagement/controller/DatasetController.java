package com.cakir.templateManagement.controller;

import com.cakir.templateManagement.dto.request.DatasetUpdateDTO;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.DatasetResponseDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.service.DatasetService;
import com.cakir.templateManagement.service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/datasets")
@RequiredArgsConstructor
public class DatasetController {

    private final DatasetService datasetService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DatasetResponseDTO>> getAll(){
        return ResponseEntity.ok(datasetService.getAllDatasets());
    }

    @GetMapping("/getByTemplateId/{templateId}")
    public ResponseEntity<DatasetResponseDTO> getByTemplateId(@PathVariable Long templateId){
        return ResponseEntity.ok(datasetService.getDatasetByTemplateId(templateId).orElse(null));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DatasetResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(datasetService.getDatasetById(id).orElse(null));
    }

    @PostMapping("/save")
    public ResponseEntity<DatasetResponseDTO> save(@RequestBody DatasetUpdateDTO datasetUpdateDTO){
        return ResponseEntity.ok(datasetService.saveDataset(datasetUpdateDTO));
    }
}
