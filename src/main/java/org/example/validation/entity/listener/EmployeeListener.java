package org.example.validation.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.example.second_level_cache.query_cache.BaseEntity;

import java.time.Instant;

public class EmployeeListener {
    @PrePersist
    public void prePersist(BaseEntity<?> employee){
        employee.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate(BaseEntity<?> employee){
        employee.setUpdatedAt(Instant.now());
    }
}
