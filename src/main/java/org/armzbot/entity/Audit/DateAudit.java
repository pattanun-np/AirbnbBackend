package org.armzbot.entity.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
public abstract class DateAudit implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createAt;


    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
}
