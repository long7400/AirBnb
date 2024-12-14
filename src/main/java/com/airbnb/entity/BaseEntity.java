package com.airbnb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        }
        this.updatedAt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
}