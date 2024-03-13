package org.example.mappingEntityAssociations.manyToMany_separateEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "usersChatsList")
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name", nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UsersChats> usersChatsList = new ArrayList<>();
}
