package org.example.second_level_cache.query_cache;

import jakarta.persistence.*;
import lombok.*;
import org.example.second_level_cache.query_cache.listener.EmployeeListener;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeeQueryCache extends BaseEntity<Long> {
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
    private DepartmentQueryCache department;
}
