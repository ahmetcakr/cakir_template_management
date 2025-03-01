package com.cakir.templateManagement.dto.request;

import lombok.Data;

@Data
public class TemplateUpdateDTO {
    private Long id;
    private String content;
    private Long userId;
}
