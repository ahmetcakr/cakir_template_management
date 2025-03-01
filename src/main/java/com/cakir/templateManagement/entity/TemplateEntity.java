package com.cakir.templateManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "templates")
public class TemplateEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
