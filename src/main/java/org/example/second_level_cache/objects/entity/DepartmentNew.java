package org.example.second_level_cache.objects.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.example.second_level_cache.objects.entity.listener.EmployeeListener;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_new_dep")
@EntityListeners(EmployeeListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "departments")
@NamedEntityGraph(name = "withEmps", attributeNodes = {
        @NamedAttributeNode("listOfEmps")
})
//@ToString(exclude = "listOfEmps")
public class DepartmentNew extends BaseEntity<Long> {
    @Column(name = "dep_name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department")
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE) // for collections also on fields
    private List<MyEmployeeNew> listOfEmps = new ArrayList<>();
    public void addEmployee(MyEmployeeNew emp){
        listOfEmps.add(emp);
        emp.setDepartment(this);
    }
}
