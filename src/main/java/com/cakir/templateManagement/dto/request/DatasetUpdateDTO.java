package com.cakir.templateManagement.dto.request;

import com.cakir.templateManagement.entity.TemplateEntity;
import com.cakir.templateManagement.enums.ApiMethod;
import com.cakir.templateManagement.enums.AuthorizationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class DatasetUpdateDTO {
    private Long id;
    private String name;
    private String apiUrl;
    private ApiMethod apiMethod;
    private boolean authRequired;
    private String apiKey;
    private AuthorizationType apiAuthorizationType;
    private String responseBody;
    private Long templateId;
}
