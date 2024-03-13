package org.example.optimistic_and_pessimistic_locking.readOnlyMode.runner;

import org.example.util.MyHibernateUtil;
import org.example.optimistic_and_pessimistic_locking.readOnlyMode.entity.Read_Only_Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Runner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
        ){
            session.createNativeQuery("SET TRANSACTION READ ONLY;").executeUpdate(); // sql level lock mode
            // session.setDefaultReadOnly(true); // update, insert, delete don't work for all session // java application level lock mode
           session.beginTransaction();
            Read_Only_Employee employee = session.createQuery("select emp from Read_Only_Employee emp where id = 1L", Read_Only_Employee.class)
                    .setReadOnly(true) // // update, insert, delete don't work for this object // java application level lock mode
                    .uniqueResult();
//            session.setReadOnly(employee, true); // update, insert, delete don't work for employee object // java application level lock mode
            employee.setSalary(333);

            session.getTransaction().commit();
        }
    }
}
