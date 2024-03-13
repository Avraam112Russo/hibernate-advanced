package org.example.second_level_cache.objects.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.second_level_cache.objects.entity.listener.EmployeeListener;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Cache;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_new_employee")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@EntityListeners(EmployeeListener.class)
@ToString(exclude = "department")
//@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "employees")
public class MyEmployeeNew extends BaseEntity<Long> {
    @Column(name = "employee_name")
    private String firstName;
    @Column(name = "employee_surname")
    private String surName;
    @Column(name = "employee_salary")
    private Integer salary;
    @Version
    private Integer version;
    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentNew department;
}
