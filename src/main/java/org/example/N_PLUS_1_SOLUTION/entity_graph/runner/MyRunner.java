package org.example.N_PLUS_1_SOLUTION.entity_graph.runner;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.N_PLUS_1_SOLUTION.entity_graph.entity.Department;
import org.example.N_PLUS_1_SOLUTION.entity_graph.entity.Employee;
import org.example.N_PLUS_1_SOLUTION.entity_graph.entity.InfoEmployee;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyRunner {
    public static void main(String[] args) {
        checkEntityGraph_withSubgraph();

    }
    private static void insertEntities(){
        Employee employee = Employee.builder().name("Elena").salary(14000).build();
        Employee employee2 = Employee.builder().name("Misha").salary(7000).build();
        InfoEmployee info = InfoEmployee.builder().address("Ivovaya street 6.1 R.41").city("Moscow").build();
        InfoEmployee info2 = InfoEmployee.builder().address("Zapovednaya street 16.1 R.28").city("Moscow").build();
        employee.addEmployeeInfo(info);
        employee2.addEmployeeInfo(info2);
        Department department = Department.builder().name("HR").build();
            department.addEmployeeToDepartment(employee);
            department.addEmployeeToDepartment(employee2);

        try (
                SessionFactory factory = MyHibernateUtil.buildSessionFactory();
                Session session = factory.openSession();
        ){
            session.beginTransaction();
//            Department department = session.get(Department.class, 1L);
            session.persist(department);
            session.getTransaction().commit();
        }
    }



    private static void checkEntityGraph(){
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            Map<String, Object> props = Map.of(
                    GraphSemantic.FETCH.getJakartaHintName(), session.getEntityGraph("withDepartment")
            );
            Employee employee = session.find(Employee.class, 1L, props);
            log.info(employee.toString());
            log.info(employee.getDepartment().getName());
            session.getTransaction().commit();
        } catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
    private static void checkEntityGraph_withList(){
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            Map<String, Object> properties = Map.of(
                    GraphSemantic.FETCH.getJakartaHintName(), session.getEntityGraph("with_list_of_employees")
            );
            log.info(session.find(Department.class, 1L, properties).toString());
            session.getTransaction().commit();
        } catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
    private static void check_query_join_fetch(){
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createQuery("select dep from Department dep join fetch dep.listOfEmployees", Department.class)
                    .list().stream().forEach(d-> System.out.println(d.toString()));
            session.getTransaction().commit();
        } catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
    private static void checkEntityGraph4(){
        try {
            @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();
            session.beginTransaction();
            Map<String, Object> props = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("with_list_of_employees")
            );
            Department department = session.find(Department.class, 1L, props);
            log.info(department.toString());
            session.getTransaction().commit();
        } catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
    private static void checkEntityGraph5(){
        try (
                SessionFactory factory = MyHibernateUtil.buildSessionFactory();
                Session session = factory.openSession();
                ){
            session.beginTransaction();
            Map<String, Object> properties = new HashMap<>();
            properties.put(GraphSemantic.LOAD.getJakartaHintName(),
                    session.getEntityGraph("withDepartmentAndInformation"));
            Employee employee = session.find(Employee.class, 1L, properties);
            log.info(employee.toString());
            session.getTransaction().commit();
        }
    }
    private static void checkEntityGraph_withQuery(){
        try (
                SessionFactory factory = MyHibernateUtil.buildSessionFactory();
                Session session = factory.openSession();
        ){
            session.beginTransaction();

            List<Employee> listOfEmps =
                    session.createQuery("select emps from Read_Only_Employee emps", Employee.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("withDepartmentAndInformation"))
                    .list();

            listOfEmps.stream().forEach(e -> System.out.println(e.toString() + "\n"));
            session.getTransaction().commit();
        }
    }
    private static void checkEntityGraph_withSubgraph(){
        try (
                SessionFactory factory = MyHibernateUtil.buildSessionFactory();
                Session session = factory.openSession();
        ){
            session.beginTransaction();
            session.createQuery("select dep from Department dep", Department.class)
                    .setHint(GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("with_list_of_employees"))
                    .list().stream().forEach(dep -> System.out.println(dep.toString() + "\n"));
            session.getTransaction().commit();
        }
    }
}
