package org.example.second_level_cache.objects.runner;

import lombok.extern.slf4j.Slf4j;
import org.example.second_level_cache.objects.entity.DepartmentNew;
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
            Map<String, Object> props = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("withEmps")
            );
            log.info( session.find(DepartmentNew.class, 1L, props).toString());
//            log.info( session.find(EmployeeQueryCache.class, 1L).getDepartment().toString());
            session.getTransaction().commit();
            try (
                 Session session1 = sessionFactory.openSession();
            ){
                session1.beginTransaction();

                log.info( session1.find(DepartmentNew.class, 1L, props).toString());
//                log.info( session1.find(EmployeeQueryCache.class, 1L).getDepartment().toString());
                session1.getTransaction().commit();
            }
        }
    }
}
