package org.example.mappingEntityAssociations.entityManyToOne;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "department_name")
    private String departmentName;
//    @OneToMany(mappedBy = "department")
//    private List<Product> listOfEmployees;
//
//    public void addToDepartment(Product employee){
//        listOfEmployees.add(employee);
//    }
}
