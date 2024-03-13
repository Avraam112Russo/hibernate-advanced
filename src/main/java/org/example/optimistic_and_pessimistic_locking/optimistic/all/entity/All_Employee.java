package org.example.optimistic_and_pessimistic_locking.optimistic.all.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_employee")
@OptimisticLocking(type = OptimisticLockType.ALL)
// select * from Employee where id = 1;
// in WHERE statement hibernate will search entity by all fields select * from Employee where id = 1 and name = 'Russo' and surname = 'Zaripov'
@DynamicUpdate
public class All_Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "salary")
    private Integer salary;

}
