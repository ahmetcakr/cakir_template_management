package com.cakir.templateManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variables")
public class VariableEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "SERIAL")
    private Long id;
    private String templateVariable;
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TemplateEntity template;
}
