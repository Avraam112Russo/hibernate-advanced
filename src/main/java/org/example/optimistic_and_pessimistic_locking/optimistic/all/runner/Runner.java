package org.example.optimistic_and_pessimistic_locking.optimistic.all.runner;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.example.optimistic_and_pessimistic_locking.optimistic.all.entity.All_Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            All_Employee emp = session.createQuery("select emp from Read_Only_Employee emp where id = 1L", All_Employee.class).uniqueResult();
            emp.setSalary(2000);
            session.persist(emp);
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
}
