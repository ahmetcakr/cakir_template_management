package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.dto.mapper.DatasetEntityMapper;
import com.cakir.templateManagement.dto.mapper.VariableEntityMapper;
import com.cakir.templateManagement.dto.request.DatasetUpdateDTO;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.DatasetResponseDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.DatasetEntity;
import com.cakir.templateManagement.entity.VariableEntity;
import com.cakir.templateManagement.repository.DatasetRepository;
import com.cakir.templateManagement.repository.VariableRepository;
import com.cakir.templateManagement.service.DatasetService;
import com.cakir.templateManagement.service.VariableService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "datasets")
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;
    private final DatasetEntityMapper datasetEntityMapper;

    @Override
    @Cacheable(key = "'getAllDatasets'", unless = "#result == null")
    public List<DatasetResponseDTO> getAllDatasets() {
        List<DatasetEntity> all = datasetRepository.findAll();
        return datasetEntityMapper.toResponseDTOs(all);
    }

    @Override
    @Cacheable(key = "'getDatasetByTemplateId:' + #templateId", unless = "#result == null")
    public Optional<DatasetResponseDTO> getDatasetByTemplateId(Long templateId) {
        DatasetEntity datasetEntity = datasetRepository.findByTemplateId(templateId).orElse(null);
        return Optional.ofNullable(datasetEntityMapper.toResponseDTO(datasetEntity));
    }

    @Override
    @Cacheable(key = "'getDatasetById:' + #id", unless = "#result == null")
    public Optional<DatasetResponseDTO> getDatasetById(Long id) {
        return datasetRepository.findById(id).map(datasetEntityMapper::toResponseDTO);
    }

    @Override
    @CacheEvict(cacheNames = "datasets", allEntries = true)
    public DatasetResponseDTO saveDataset(DatasetUpdateDTO datasetUpdateDTO) {
        return datasetEntityMapper.toResponseDTO(datasetRepository.save(datasetEntityMapper.toEntity(datasetUpdateDTO)));
    }
}
