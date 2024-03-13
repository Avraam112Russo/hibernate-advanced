package org.example.DAO;

import org.example.DAO.repository.EmployeeRepository;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class Runner {
    public static void main(String[] args) {
        try (SessionFactory factory = MyHibernateUtil.buildSessionFactory()){
            Session session =(Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy1, method, args1) -> method.invoke(factory.getCurrentSession(), args1));
            session.beginTransaction();
            EmployeeRepository repository = new EmployeeRepository(session);
            repository.findById(1L).ifPresent(e -> System.out.println(e));

            session.getTransaction().commit();
        }
    }
}
