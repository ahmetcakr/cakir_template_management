package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.dto.mapper.VariableEntityMapper;
import com.cakir.templateManagement.dto.request.VariableUpdateDTO;
import com.cakir.templateManagement.dto.response.VariableResponseDTO;
import com.cakir.templateManagement.entity.VariableEntity;
import com.cakir.templateManagement.repository.VariableRepository;
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
@CacheConfig(cacheNames = "variables")
public class VariableServiceImpl implements VariableService {

    private final VariableRepository variableRepository;
    private final VariableEntityMapper variableEntityMapper;

    @Override
    @Cacheable(key = "'getAllVariables'", unless = "#result == null")
    public List<VariableResponseDTO> getAllVariables() {
        List<VariableEntity> all = variableRepository.findAll();
        return variableEntityMapper.entityToResponseDTOs(all);
    }

    @Override
    @Cacheable(key = "'getVariablesByTemplateId:' + #templateId", unless = "#result == null")
    public List<VariableResponseDTO> getVariablesByTemplateId(Long templateId) {
        List<VariableEntity> allByTemplateId = variableRepository.findAllByTemplateId(templateId);
        return variableEntityMapper.entityToResponseDTOs(allByTemplateId);
    }

    @Override
    @Cacheable(key = "'getVariableById:' + #id", unless = "#result == null")
    public Optional<VariableResponseDTO> getVariableById(Long id) {
        return variableRepository.findById(id).map(variableEntityMapper::toResponseDTO);
    }

    @Override
    @CacheEvict(cacheNames = "variables", allEntries = true)
    public VariableResponseDTO saveVariable(VariableUpdateDTO variableUpdateDTO) {
        return variableEntityMapper.toResponseDTO(variableRepository.save(variableEntityMapper.toEntity(variableUpdateDTO)));
    }
}
