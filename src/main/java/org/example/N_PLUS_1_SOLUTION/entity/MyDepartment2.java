package org.example.N_PLUS_1_SOLUTION.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "listOfEmployees")
@Entity
@Table(name = "t_department")
public class MyDepartment2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer id;
    @Column(name = "department_name")
    private String departmentName;
    @Builder.Default
//    @Fetch(FetchMode.SUBSELECT) // subSelect available for collections only
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MyEmployee> listOfEmployees = new ArrayList<>();

    public void addEmployeeToDepartment(MyEmployee employee){
        listOfEmployees.add(employee);
        employee.setDepartment(this);
    }
}
