package org.example.mappingEntityAssociations.entityOneToOne.test;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "accountNumber")
@ToString(exclude = "employee")
@Entity
@Table(name = "bank_credentials")
public class BankCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_account_id")
    private Integer accountId;
    @Column(name = "personal_account_number", nullable = false, unique = true)
    private String accountNumber;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private NewEmployee employee;

    public void setEmployee(NewEmployee employee) {
        employee.setBankCredential(this);
        this.employee = employee;
    }
}
