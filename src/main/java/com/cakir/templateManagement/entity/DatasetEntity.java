package com.cakir.templateManagement.entity;

import com.cakir.templateManagement.enums.ApiMethod;
import com.cakir.templateManagement.enums.AuthorizationType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "datasets")
public class DatasetEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "SERIAL")
    private Long id;

    private String name;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "api_method")
    @Enumerated(EnumType.STRING)
    private ApiMethod apiMethod;

    @Column(name = "auth_required")
    private boolean authRequired;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_authorization_type")
    @Enumerated(EnumType.STRING)
    private AuthorizationType apiAuthorizationType;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @OneToOne
    @JoinColumn(name = "template_id")
    private TemplateEntity template;
}
