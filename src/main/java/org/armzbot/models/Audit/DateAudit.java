package org.armzbot.models.Audit;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

public abstract  class DateAudit implements Serializable {


    private static final  long serialVersionUID = 1L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createAt;


    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
}
