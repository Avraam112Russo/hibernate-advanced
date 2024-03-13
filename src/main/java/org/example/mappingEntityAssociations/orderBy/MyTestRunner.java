package org.example.mappingEntityAssociations.orderBy;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class MyTestRunner {
    public static void main(String[] args) {
        try {
           @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
           @Cleanup Session session = sessionFactory.openSession();
           session.beginTransaction();

//           MyEmployee employee = MyEmployee.builder()
//                   .name("Alex")
//                   .build();
//            MyEmployee employee2 = MyEmployee.builder()
//                    .name("Denis")
//                    .build();
//            MyDepartment department = session.get(MyDepartment.class, 1);
//
//           department.addEmployeeToDepartment(employee);
//           department.addEmployeeToDepartment(employee2);
//           session.persist(department);

            MyDepartment department = session.get(MyDepartment.class, 1);
            department.getEmployeeList().forEach(employee -> log.info("All_Employee info: {}", employee));
           session.getTransaction().commit();
        }catch (Exception exception){log.error("Error occurred: ", exception);
        }
    }
}
