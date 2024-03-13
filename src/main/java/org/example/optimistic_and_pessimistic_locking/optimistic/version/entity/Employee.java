package org.example.optimistic_and_pessimistic_locking.optimistic.version.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Table(name = "table_employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "employee_salary")
    private Integer salary;
    @Version
    private Integer version;
}
