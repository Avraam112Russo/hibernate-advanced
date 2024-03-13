package org.example.N_PLUS_1_SOLUTION.entity_graph.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@ToString(exclude = "listOfEmployees")
@Table(name = "t_department")
@NamedEntityGraph(name = "with_list_of_employees",
        attributeNodes = {
        @NamedAttributeNode(value = "listOfEmployees", subgraph = "With_employee_info")
},
        // fetch employee with employeeInfo
        subgraphs = {
        @NamedSubgraph(name = "With_employee_info", attributeNodes = @NamedAttributeNode("infoEmployee"))
}

)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    @Column(name = "department_name", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "department",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @Builder.Default
    private List<Employee> listOfEmployees = new ArrayList<>();

    public void addEmployeeToDepartment(Employee employee){
        listOfEmployees.add(employee);
        employee.setDepartment(this);
    }
}
