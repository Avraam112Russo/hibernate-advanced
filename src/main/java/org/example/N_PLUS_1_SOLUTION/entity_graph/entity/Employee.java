package org.example.N_PLUS_1_SOLUTION.entity_graph.entity;


import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "department")
@Table(name = "t_employees")
@NamedEntityGraph(name = "withDepartmentAndInformation", attributeNodes = {
        @NamedAttributeNode("department"),
        @NamedAttributeNode("infoEmployee")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "employee_salary")
    private Integer salary;
    @JoinColumn(name = "department_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Department  department;
    @JoinColumn(name = "employee_info_id", nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private InfoEmployee infoEmployee;
    public void addEmployeeInfo(InfoEmployee infoEmployee){
        this.infoEmployee = infoEmployee;
        infoEmployee.setEmployee(this);
    }
}
