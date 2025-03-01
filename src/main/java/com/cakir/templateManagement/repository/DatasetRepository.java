package com.cakir.templateManagement.repository;

import com.cakir.templateManagement.entity.DatasetEntity;
import com.cakir.templateManagement.entity.VariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository<DatasetEntity, Long> {
    Optional<DatasetEntity> findByTemplateId(Long templateId);
}
