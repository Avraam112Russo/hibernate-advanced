package org.example.mappingEntityAssociations.orderBy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_department")
public class MyDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    @OrderBy(value = "name ASC")
    private List<MyEmployee> employeeList = new ArrayList<>();

    public void addEmployeeToDepartment(MyEmployee employee){
        employeeList.add(employee);
        employee.setDepartment(this);
    }
}
