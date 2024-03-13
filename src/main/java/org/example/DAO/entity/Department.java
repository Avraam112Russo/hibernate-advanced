package org.example.DAO.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DAO.entity.listener.EmployeeListener;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "my_new_dep")
@EntityListeners(EmployeeListener.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedEntityGraph(name = "withEmps", attributeNodes = {
        @NamedAttributeNode("listOfEmps")
})
//@ToString(exclude = "listOfEmps")
public class Department extends BaseEntity<Long> {
    @Column(name = "dep_name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department")
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) // for collections also on fields
    private List<MyEmployee> listOfEmps = new ArrayList<>();
    public void addEmployee(MyEmployee emp){
        listOfEmps.add(emp);
        emp.setDepartment(this);
    }
}
