package org.example.HQL.employees;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"depName", "avgSalary"})

//@NamedNativeQuery(name = "emps",
//                query = "select dep.departmentName, avg(emps.salary), sum (emps.salary), dep.id " +
//                        "from Department_Hql dep " +
//                        "join dep.listOfEmployees emps " +
//                        " group by dep.departmentName ",
//                resultSetMapping = "AverageSalary_DepartmentNameMapping")
//@SqlResultSetMapping(
//        name = "AverageSalary_DepartmentNameMapping",
//        classes = @ConstructorResult(
//                targetClass = AverageSalary_DepartmentName.class,
//                columns = {
//                        @ColumnResult(name = "depName", type = String.class),
//                        @ColumnResult(name = "avgSalary", type = double.class),
//                        @ColumnResult(name = "sumSalary", type = int.class),
//                        @ColumnResult(name = "departmentId", type = String.class)
//                }
//        )
//)
public class AverageSalary_DepartmentName {
    @Id
    private String depName;
    private Integer depId;
    private Double avgSalary;
    private Integer sumSalary;
}
