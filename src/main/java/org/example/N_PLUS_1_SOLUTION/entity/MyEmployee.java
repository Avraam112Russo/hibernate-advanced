package org.example.N_PLUS_1_SOLUTION.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_employees")
@ToString(exclude = {"listOfEmployeeChats", "department"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@FetchProfile(name = "someNameFetchProfile", fetchOverrides = {
//        @FetchProfile.FetchOverride(entity = MyEmployee.class, association = "department", mode = FetchMode.JOIN)
//})

@NamedEntityGraph(name = "withDepartment", attributeNodes = {
        @NamedAttributeNode("department") // one join select
        // also we can use @NamedSubGraphs
        // for example: user -> entityGraph: usersChats -> SubGraphs: chats
})
public class MyEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private MyDepartment2 department;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @Fetch(FetchMode.SUBSELECT)
    @Builder.Default
    private List<EmployeeChats> listOfEmployeeChats = new ArrayList<>();
}
