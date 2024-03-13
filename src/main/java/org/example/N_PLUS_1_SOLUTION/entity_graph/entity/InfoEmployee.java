package org.example.N_PLUS_1_SOLUTION.entity_graph.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_info")
@ToString(exclude = "employee")
@Builder
public class InfoEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @OneToOne(mappedBy = "infoEmployee", fetch = FetchType.LAZY)
    private Employee employee;
}
