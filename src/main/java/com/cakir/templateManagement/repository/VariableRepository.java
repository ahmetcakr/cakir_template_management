package com.cakir.templateManagement.repository;

import com.cakir.templateManagement.entity.VariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends JpaRepository<VariableEntity, Long> {
    List<VariableEntity> findAllByTemplateId(Long templateId);
}
