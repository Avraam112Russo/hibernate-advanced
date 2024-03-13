package org.example.inheritance.SINGLE_TABLE;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("manager")
public class Manager extends User {
    private String project;

    @Builder
    public Manager(Integer id, String name, String lastName, String project) {
        super(id, name, lastName);
        this.project = project;
    }
}
