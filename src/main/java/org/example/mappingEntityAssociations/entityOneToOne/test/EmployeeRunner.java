package org.example.mappingEntityAssociations.entityOneToOne.test;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class EmployeeRunner {
    public static void main(String[] args) {
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();

            session.beginTransaction();

//            NewEmployee employee = NewEmployee.builder().email("Ruslan@mail.com").build();
//            BankCredential bankCredential = BankCredential.builder().accountNumber(UUID.randomUUID().toString()).build();
//            bankCredential.setEmployee(employee);
//            session.persist(employee);

            NewEmployee employee = session.get(NewEmployee.class, 1);
            log.info(employee.toString());
            log.info(employee.getBankCredential().toString());
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
}
