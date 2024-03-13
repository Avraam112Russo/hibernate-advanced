package org.example.mappingEntityAssociations.entityOneToOne.test;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
@ToString(exclude = "bankCredential")
@Entity
@Table(name = "new_employees")
public class NewEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BankCredential bankCredential;
}
