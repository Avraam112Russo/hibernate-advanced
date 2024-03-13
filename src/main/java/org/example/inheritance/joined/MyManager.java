package org.example.inheritance.joined;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import org.example.inheritance.SINGLE_TABLE.User;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_manager")
@PrimaryKeyJoinColumn(name = "id") // primary key and foreign key in one moment
public class MyManager extends MyUser {
    private String project;

    @Builder
    public MyManager(Integer id, String name, String lastName, String project) {
        super(id, name, lastName);
        this.project = project;
    }
}
