package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.dto.mapper.VariableMappingEntityMapper;
import com.cakir.templateManagement.dto.request.VariableMappingUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableMappingResponseDTO;
import com.cakir.templateManagement.entity.VariableMappingEntity;
import com.cakir.templateManagement.repository.VariableMappingRepository;
import com.cakir.templateManagement.service.VariableMappingService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "variables")
public class VariableMappingServiceImpl implements VariableMappingService {

    private final VariableMappingRepository variableMappingRepository;
    private final VariableMappingEntityMapper variableMappingEntityMapper;

    @Override
    @Cacheable(key = "'getAllVariables'", unless = "#result == null")
    public List<VariableMappingResponseDTO> getAllVariables() {
        List<VariableMappingEntity> all = variableMappingRepository.findAll();
        return variableMappingEntityMapper.toResponseDTOs(all);
    }

    @Override
    @Cacheable(key = "'getVariablesByTemplateId:' + #templateId", unless = "#result == null")
    public List<VariableMappingResponseDTO> getVariablesByTemplateId(Long templateId) {
        List<VariableMappingEntity> allByTemplateId = variableMappingRepository.findAllByTemplateId(templateId);
        return variableMappingEntityMapper.toResponseDTOs(allByTemplateId);
    }

    @Override
    @Cacheable(key = "'getVariableById:' + #id", unless = "#result == null")
    public Optional<VariableMappingResponseDTO> getVariableById(Long id) {
        return variableMappingRepository.findById(id).map(variableMappingEntityMapper::toResponseDTO);
    }

    @Override
    @CacheEvict(cacheNames = "variables", allEntries = true)
    public VariableMappingResponseDTO saveVariable(VariableMappingUpdateDTO variableMappingUpdateDTO) {
        return variableMappingEntityMapper.toResponseDTO(variableMappingRepository.save(variableMappingEntityMapper.toEntity(variableMappingUpdateDTO)));
    }
}
