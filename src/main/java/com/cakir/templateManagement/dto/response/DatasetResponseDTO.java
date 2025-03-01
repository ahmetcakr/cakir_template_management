package com.cakir.templateManagement.dto.response;

import com.cakir.templateManagement.enums.ApiMethod;
import com.cakir.templateManagement.enums.AuthorizationType;
import lombok.Data;

@Data
public class DatasetResponseDTO {
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
