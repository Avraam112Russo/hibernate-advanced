package org.example.mappingEntityAssociations.manyToMany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "userName")
@ToString(exclude = "myChatList")
@Entity
@Table(name = "my_users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_chats",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    @Builder.Default
    private List<MyChat> myChatList = new ArrayList<>();

    public void addToChat(MyChat chat){
        myChatList.add(chat);
        chat.getMyUserList().add(this);
    }
}
