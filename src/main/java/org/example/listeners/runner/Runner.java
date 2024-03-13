package org.example.listeners.runner;

import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.example.listeners.runner.entity.MyEmployee;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
        ){
            session.beginTransaction();
//            MyEmployee employee = MyEmployee.builder()
//                    .firstName("Avraam")
//                    .surName("Russo")
//                    .salary(12000)
//                    .build();
            MyEmployee employee = session.createQuery("select e from EmployeeQueryCache e where id = 1L", MyEmployee.class)
                    .setLockMode(LockModeType.OPTIMISTIC)
                    .uniqueResult();
            employee.setSalary(20000);
            session.persist(employee);
            session.getTransaction().commit();
        }
    }
}
