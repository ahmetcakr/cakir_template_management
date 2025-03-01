package com.cakir.templateManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variable_mappings")
public class VariableMappingEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String templateVariable;
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TemplateEntity template;
}
