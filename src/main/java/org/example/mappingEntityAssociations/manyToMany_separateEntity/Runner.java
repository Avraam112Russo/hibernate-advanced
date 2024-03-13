package org.example.mappingEntityAssociations.manyToMany_separateEntity;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Instant;

@Slf4j
public class Runner {
    public static void main(String[] args) {

        try {
          @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
          @Cleanup Session session = sessionFactory.openSession();
          session.beginTransaction();
          User user = User.builder()
                  .name("Elena")
                  .build();
//          Chat chat = Chat.builder()
//                  .name("footballers")
//                  .build();
            Chat chat = session.get(Chat.class, 1);
            UsersChats usersChats = UsersChats.builder()
                    .createdAt(Instant.now())
                  .build();
          usersChats.addUser(user);
          usersChats.addChat(chat);
          session.persist(usersChats);
          session.getTransaction().commit();
        }catch (Exception exception){
        log.error("Error occurred: ", exception);
        }
    }
}
