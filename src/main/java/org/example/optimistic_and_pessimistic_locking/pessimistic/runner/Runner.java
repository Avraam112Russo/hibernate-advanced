package org.example.optimistic_and_pessimistic_locking.pessimistic.runner;

import jakarta.persistence.LockModeType;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.example.optimistic_and_pessimistic_locking.pessimistic.entity.Pessimistic_Lock_Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();

            session.beginTransaction();
            Pessimistic_Lock_Employee employee = session.find(Pessimistic_Lock_Employee.class, 1L,
                    LockModeType.PESSIMISTIC_READ); // lock following operations: update, delete, select for update on database level
            employee.setSalary(3000);
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
}
