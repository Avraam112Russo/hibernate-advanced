package org.example.HQL.employees;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
@Entity
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(of = "departmentName", callSuper = false)
public class Department_Hql extends BaseEntityEmployee<Integer>{
    @Column(name = "department_name")
    private String departmentName;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "department", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Employee_Hql> listOfEmployees = new ArrayList<>();

    public void addEmployeeToDepartment(Employee_Hql employee_hql){
        listOfEmployees.add(employee_hql);
        employee_hql.setDepartment(this);
    }
}
