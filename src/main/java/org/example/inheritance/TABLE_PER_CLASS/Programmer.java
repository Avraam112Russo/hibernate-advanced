package org.example.inheritance.TABLE_PER_CLASS;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "programmer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class Programmer extends User{
    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Integer id, String firstName, String lastName, String email, Language language) {
        super(id, firstName, lastName, email);
        this.language = language;
    }
}
