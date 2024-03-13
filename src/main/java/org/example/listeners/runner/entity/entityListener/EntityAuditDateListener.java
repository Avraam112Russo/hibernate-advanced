package org.example.listeners.runner.entity.entityListener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.example.listeners.runner.entity.BaseEntity;

import java.time.Instant;

public class EntityAuditDateListener {
    @PrePersist
    public void prePersist(BaseEntity<?> employee){
        employee.setCreatedAt(Instant.now());
    }
    @PreUpdate
    public void preUpdate(BaseEntity<?> employee){
        employee.setUpdatedAt(Instant.now());
    }
}
