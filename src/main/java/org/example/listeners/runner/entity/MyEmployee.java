package org.example.listeners.runner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listeners.runner.entity.entityListener.EntityAuditDateListener;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_employee")
@EntityListeners(EntityAuditDateListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class MyEmployee extends BaseEntity<Long>{
    @Column(name = "employee_name")
    private String firstName;
    @Column(name = "employee_surname")
    private String surName;
    @Column(name = "employee_salary")
    private Integer salary;
    @Version
    private Integer version;


}
