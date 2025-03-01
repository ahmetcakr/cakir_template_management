package com.cakir.templateManagement.dto.request;

import lombok.Data;

@Data
public class TemplateCreateDTO {
    private String content;
    private Long userId;
}
