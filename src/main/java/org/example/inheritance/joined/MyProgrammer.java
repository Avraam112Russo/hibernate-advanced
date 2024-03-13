package org.example.inheritance.joined;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import org.example.inheritance.SINGLE_TABLE.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_prpgrammer")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class MyProgrammer extends MyUser {
    private String language;

    @Builder
    public MyProgrammer(Integer id, String name, String lastName, String language) {
        super(id, name, lastName);
        this.language = language;
    }
}
