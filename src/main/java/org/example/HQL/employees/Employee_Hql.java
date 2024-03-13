package org.example.HQL.employees;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
@Builder
@ToString(callSuper = true, exclude = "department")
@EqualsAndHashCode(of = "userName", callSuper = false)
public class Employee_Hql extends BaseEntityEmployee<Long>{
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "salary")
    private Integer salary;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department_Hql department;
}
