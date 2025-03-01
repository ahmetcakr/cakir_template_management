package com.cakir.templateManagement.repository;

import com.cakir.templateManagement.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
    List<TemplateEntity> findAllByUserId(Long userId);
}
