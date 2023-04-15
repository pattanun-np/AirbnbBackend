package org.armzbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.armzbot.entity.Audit.DateAudit;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class BaseEntity extends DateAudit {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 30, unique = true, nullable = false, updatable = false)
    private String id;
}
