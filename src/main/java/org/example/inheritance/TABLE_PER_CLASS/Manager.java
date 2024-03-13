package org.example.inheritance.TABLE_PER_CLASS;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manager")
@Data
@ToString(callSuper = true)
public class Manager extends User{
    private String project;
    @Builder
    public Manager(Integer id, String firstName, String lastName, String email, String project) {
        super(id, firstName, lastName, email);
        this.project = project;
    }
}
