package org.example.mappingEntityAssociations.manyToMany;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class ApplicationRunner {
    public static void main(String[] args) {
        try {
//            MyUser user = MyUser.builder().userName("elenasok001").build();
//            MyChat chat = MyChat.builder().build();

            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            MyChat chat = session.get(MyChat.class, 1);
            log.info("chat id: {} \n Users: {}", chat.getChatId(), chat.getMyUserList());
//            user.addToChat(chat);
//            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception exception){
        log.error("Error occurred: ", exception);
        }
    }
}
