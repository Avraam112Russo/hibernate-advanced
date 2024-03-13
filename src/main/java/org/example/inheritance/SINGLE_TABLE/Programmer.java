package org.example.inheritance.SINGLE_TABLE;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("programmer")
public class Programmer extends User {
    private String language;

    @Builder
    public Programmer(Integer id, String name, String lastName, String language) {
        super(id, name, lastName);
        this.language = language;
    }
}
