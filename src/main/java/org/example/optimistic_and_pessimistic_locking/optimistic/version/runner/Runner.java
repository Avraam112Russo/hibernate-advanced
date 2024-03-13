package org.example.optimistic_and_pessimistic_locking.optimistic.version.runner;

import jakarta.persistence.LockModeType;
import org.example.util.MyHibernateUtil;
import org.example.optimistic_and_pessimistic_locking.optimistic.version.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Runner {
    public static void main(String[] args) {
        try (SessionFactory factory = MyHibernateUtil.buildSessionFactory();
             Session session = factory.openSession();
        ){
            session.beginTransaction();
            Employee emps = session.createQuery("select emp from Read_Only_Employee emp where emp.name='Vasya'", Employee.class)
                    .setLockMode(LockModeType.OPTIMISTIC)
                    .uniqueResult();

            emps.setSalary(100);
            session.persist(emps);

            session.getTransaction().commit();
        }
    }
}
