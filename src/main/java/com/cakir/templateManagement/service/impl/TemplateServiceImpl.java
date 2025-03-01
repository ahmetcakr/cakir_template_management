package com.cakir.templateManagement.service.impl;

import com.cakir.templateManagement.dto.mapper.TemplateEntityMapper;
import com.cakir.templateManagement.dto.request.TemplateUpdateDTO;
import com.cakir.templateManagement.dto.response.TemplateResponseDTO;
import com.cakir.templateManagement.entity.TemplateEntity;
import com.cakir.templateManagement.repository.TemplateRepository;
import com.cakir.templateManagement.service.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "templates")
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateEntityMapper templateEntityMapper;

    @Override
    @Cacheable(key = "'getAllTemplates'", unless = "#result == null")
    public List<TemplateResponseDTO> getAllTemplates() {
        List<TemplateEntity> all = templateRepository.findAll();
        return templateEntityMapper.toResponseDTOs(all);
    }

    @Override
    @Cacheable(key = "'getTemplatesByUserId:' + #userId", unless = "#result == null")
    public List<TemplateResponseDTO> getTemplatesByUserId(Long userId) {
        List<TemplateEntity> allByUserId = templateRepository.findAllByUserId(userId);
        return templateEntityMapper.toResponseDTOs(allByUserId);
    }

    @Override
    @Cacheable(key = "'getTemplateById:' + #id", unless = "#result == null")
    public Optional<TemplateResponseDTO> getTemplateById(Long id) {
        return templateRepository.findById(id).map(templateEntityMapper::toResponseDTO);
    }

    @Override
    @CacheEvict(cacheNames = "templates", allEntries = true)
    public TemplateResponseDTO saveTemplate(TemplateUpdateDTO templateUpdateDTO) {
        return templateEntityMapper.toResponseDTO(templateRepository.save(templateEntityMapper.toEntity(templateUpdateDTO)));
    }
}
