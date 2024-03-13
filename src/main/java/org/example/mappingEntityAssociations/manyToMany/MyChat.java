package org.example.mappingEntityAssociations.manyToMany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "chatId")
@ToString(exclude = "myUserList")
@Entity
@Table(name = "my_chats")
public class MyChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;
    @Builder.Default
    @ManyToMany(mappedBy = "myChatList")
    private List<MyUser> myUserList = new ArrayList<>();
}
