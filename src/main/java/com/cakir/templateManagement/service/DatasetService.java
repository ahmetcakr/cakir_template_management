package com.cakir.templateManagement.service;

import com.cakir.templateManagement.dto.request.DatasetUpdateDTO;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.DatasetResponseDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;

import java.util.List;
import java.util.Optional;

public interface DatasetService {
    List<DatasetResponseDTO> getAllDatasets();
    Optional<DatasetResponseDTO> getDatasetByTemplateId(Long templateId);
    Optional<DatasetResponseDTO> getDatasetById(Long datasetId);
    DatasetResponseDTO saveDataset(DatasetUpdateDTO datasetUpdateDTO);
}
