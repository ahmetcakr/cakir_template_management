package com.cakir.templateManagement.repository;

import com.cakir.templateManagement.entity.VariableMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableMappingRepository extends JpaRepository<VariableMappingEntity, Long> {
    List<VariableMappingEntity> findAllByTemplateId(Long templateId);
}
