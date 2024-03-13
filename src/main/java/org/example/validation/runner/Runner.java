package org.example.validation.runner;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.example.validation.entity.Department;
import org.example.validation.entity.MyEmployee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
@Slf4j
public class Runner {
    public static void main(String[] args) {
        try {

            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            Department department = Department.builder()
                    .name("SALES")
                    .build();
            MyEmployee employee = MyEmployee.builder()
                    .firstName("Avraam")
                    .surName("Russo")
                    .salary(1000)
                    .build();
            department.addEmployee(employee);
            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
}
