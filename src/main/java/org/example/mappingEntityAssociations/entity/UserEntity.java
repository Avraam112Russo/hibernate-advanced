package org.example.mappingEntityAssociations.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userentity", schema = "public")
public class UserEntity {


    @EmbeddedId
    //primary key
    private InfoUser infoUser;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;



}
