package org.example.mappingEntityAssociations.entityOneToOne;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "userName")
@ToString(exclude = "userProfile")
@Entity
@Table(name = "social_media_users")
public class SocialMediaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

}
