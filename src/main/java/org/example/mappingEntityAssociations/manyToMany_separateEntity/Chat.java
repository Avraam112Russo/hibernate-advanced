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
@Table(name = "chats")
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;
    @Column(name = "name")
    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private List<UsersChats> usersChatsList = new ArrayList<>();
}
