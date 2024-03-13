package org.example.mappingEntityAssociations.entityOneToOne;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
@ToString(exclude = "user")
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Integer userProfileId;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id")
    private SocialMediaUser user;

    public void setUser(SocialMediaUser user){
        user.setUserProfile(this);
        this.user = user;
    }
}
