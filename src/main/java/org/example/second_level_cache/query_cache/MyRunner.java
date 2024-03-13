package org.example.second_level_cache.query_cache;

import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import java.util.Map;

@Slf4j
public class MyRunner {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
                Session session = sessionFactory.openSession();
                ){
//            EmployeeQueryCache employee = EmployeeQueryCache.builder()
//                    .firstName("Avraam")
//                    .surName("Russo")
//                    .salary(10000)
//                    .build();
//            DepartmentNew department = DepartmentNew.builder()
//                    .name("IT")
//                    .build();
//            department.addEmployee(employee);




            session.beginTransaction();

            DepartmentQueryCache department = session.createQuery("select dep from DepartmentQueryCache dep where dep.id= 1L", DepartmentQueryCache.class)
                    .setReadOnly(true)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("withEmps"))
                    .setCacheable(true)
                    .uniqueResult();
            log.info("Result: {}", department.toString());
            session.getTransaction().commit();
            try (
                 Session session1 = sessionFactory.openSession();
            ){
                session1.beginTransaction();
                DepartmentQueryCache department2 = session1.createQuery("select dep from DepartmentQueryCache dep where dep.id= 1L", DepartmentQueryCache.class)
                        .setReadOnly(true)
                        .setHint(GraphSemantic.LOAD.getJakartaHintName(), session1.getEntityGraph("withEmps"))
                        .setCacheable(true)
                        .uniqueResult();
                log.info("Result: {}", department2.toString());
                session1.getTransaction().commit();
            }
        }
    }
}
