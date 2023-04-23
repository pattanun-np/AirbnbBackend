package org.armzbot.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    private static final long serialVersionUID = 1L;

    @CreatedDate
    private LocalDateTime createAt = LocalDateTime.now();


    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    public LocalDateTime getCreatedDate() {
        return createAt;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createAt = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return updatedAt;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.updatedAt = modifiedDate;
    }

}
