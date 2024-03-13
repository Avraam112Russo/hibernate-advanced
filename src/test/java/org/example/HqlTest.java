package org.example;

import jakarta.persistence.FlushModeType;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.HQL.employees.AverageSalary_DepartmentName;
import org.example.HQL.employees.Department_Hql;
import org.example.HQL.employees.Employee_Hql;
import org.example.HQL.Product_Hql;
import org.example.util.MyHibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@Slf4j
public class HqlTest {

    @Test
    void checkHQL_2(){
        Employee_Hql employee_hql = Employee_Hql.builder()
                .userName("avraam112russo")
                .salary(13000)
                .build();
        Employee_Hql employee_hql2 = Employee_Hql.builder()
                .userName("elena@sok")
                .salary(17500)
                .build();
        Employee_Hql employee_hql3 = Employee_Hql.builder()
                .userName("michel@ballack")
                .salary(5500)
                .build();
        Department_Hql department_hql = Department_Hql.builder()
                .departmentName("IT")
                .build();
        department_hql.addEmployeeToDepartment(employee_hql);
        department_hql.addEmployeeToDepartment(employee_hql2);
        department_hql.addEmployeeToDepartment(employee_hql3);


        Employee_Hql employee_hql4 = Employee_Hql.builder()
                .userName("sasha@petrov")
                .salary(7500)
                .build();
        Employee_Hql employee_hql5 = Employee_Hql.builder()
                .userName("vasya@ivanov")
                .salary(10500)
                .build();
        Employee_Hql employee_hql6 = Employee_Hql.builder()
                .userName("petya@popov")
                .salary(5500)
                .build();
        Department_Hql department_hql2 = Department_Hql.builder()
                .departmentName("SALE")
                .build();
        department_hql2.addEmployeeToDepartment(employee_hql4);
        department_hql2.addEmployeeToDepartment(employee_hql5);
        department_hql2.addEmployeeToDepartment(employee_hql6);

        @Cleanup SessionFactory factory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = factory.openSession();
        session.beginTransaction();
        session.persist(department_hql);
        session.persist(department_hql2);
        session.flush();

        ;
//        log.info("RESULT: {}", session.createQuery("SELECT emp.department.departmentName FROM Employee_Hql emp where userName = :username", String.class)
//                .setParameter("username", employee_hql2.getUserName()).uniqueResult().toString());
//        log.info(session.createQuery("select emp from Employee_Hql emp order by emp.salary ASC", Employee_Hql.class)
//                .setMaxResults(2) // limit
//                .list().toString());




//        List<Employee_Hql>employee_hqls = session.createQuery("select emp from Department_Hql dep join dep.listOfEmployees emp where dep.departmentName = :depName",
//                Employee_Hql.class)
//                .setParameter("depName", "SALE")
//                .list();
//        log.info("Result: {}", employee_hqls.toString());







//        Optional<Double> avgSalary =
//                session.createQuery("select avg (emp.salary) from Department_Hql dep join dep.listOfEmployees emp " +
//                                "where dep.departmentName = :depName", Double.class)
//                        .setParameter("depName", "IT")
//                .uniqueResultOptional();
//        if (avgSalary.isPresent()){log.info("Average salary: {}", avgSalary.get());}






//        List<Object[]> objectsList = session.createQuery("select dep.departmentName, avg(emps.salary)" +
//                " from Department_Hql dep join dep.listOfEmployees emps group by dep.departmentName", Object[].class).list();
//        for (Object[] object: objectsList){
//        log.info("=======RESULT========: {}", Arrays.toString(object));
//        }




//        log.info("=======RESULT========: {}", session.createQuery("select dep.departmentName, avg(emps.salary) from Department_Hql dep " +
//                        "join dep.listOfEmployees emps group by dep.departmentName",
//                AverageSalary_DepartmentName.class).list().toString());




        log.info("=======RESULT========: {}", session.createQuery("select dep.departmentName, avg(emps.salary) " +
                                " from Department_Hql dep " +
                                "join dep.listOfEmployees emps group by dep.departmentName " +
                                "having avg(emps.salary) > 10000",
                        AverageSalary_DepartmentName.class)

                .list().toString());

        session.getTransaction().commit();
    }
    @Test
    void checkHQL(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Product_Hql product_hql = Product_Hql.builder()
                .name("Apple Iphone 15 Pro")
                .price(4000)
                .build();
        Product_Hql product_hql2 = Product_Hql.builder()
                .name("Samsung S22")
                .price(3400)
                .build();
        Product_Hql product_hql3 = Product_Hql.builder()
                .name("Huawei P30")
                .price(6900)
                .build();
        session.persist(product_hql);
        session.persist(product_hql2);
        session.persist(product_hql3);
        session.flush();

        Optional<Product_Hql> optionalProduct_hql = session.createQuery("select p from Product_Hql p where p.name = :name and price > :price", Product_Hql.class)
                .setParameter("name", "Samsung S22")
                .setParameter("price", 1000)
                .setFlushMode(FlushModeType.AUTO) // default // hibernate query auto call .flush() method
                .uniqueResultOptional();
        List<Product_Hql> list = session.createNamedQuery("findAllProducts", Product_Hql.class)
                .list();
        if (optionalProduct_hql.isPresent()){
            log.info("Result: {}", optionalProduct_hql.get());
        }
        log.info(list.toString());

//        session.createQuery("update Product_Hql prod set name = 'newName' where prod.name = 'Samsung S22'").executeUpdate();

        Product_Hql product_hql1 = session.createNativeQuery("SELECT * from table_product where name = 'Samsung S22'", Product_Hql.class).uniqueResult();

        log.info("Native sql query result: {}",product_hql1.toString());
        session.getTransaction().commit();

    }

    @Test
    void checkHQL_3(){
        Employee_Hql employee_hql = Employee_Hql.builder()
                .userName("avraam112russo")
                .salary(13000)
                .build();
        Employee_Hql employee_hql2 = Employee_Hql.builder()
                .userName("elena@sok")
                .salary(17500)
                .build();
        Employee_Hql employee_hql3 = Employee_Hql.builder()
                .userName("michel@ballack")
                .salary(5500)
                .build();
        Department_Hql department_hql = Department_Hql.builder()
                .departmentName("IT")
                .build();
        department_hql.addEmployeeToDepartment(employee_hql);
        department_hql.addEmployeeToDepartment(employee_hql2);
        department_hql.addEmployeeToDepartment(employee_hql3);


        Employee_Hql employee_hql4 = Employee_Hql.builder()
                .userName("sasha@petrov")
                .salary(7500)
                .build();
        Employee_Hql employee_hql5 = Employee_Hql.builder()
                .userName("vasya@ivanov")
                .salary(10500)
                .build();
        Employee_Hql employee_hql6 = Employee_Hql.builder()
                .userName("petya@popov")
                .salary(5500)
                .build();
        Department_Hql department_hql2 = Department_Hql.builder()
                .departmentName("SALE")
                .build();
        department_hql2.addEmployeeToDepartment(employee_hql4);
        department_hql2.addEmployeeToDepartment(employee_hql5);
        department_hql2.addEmployeeToDepartment(employee_hql6);

        @Cleanup SessionFactory factory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = factory.openSession();
        session.beginTransaction();
        session.persist(department_hql);
        session.persist(department_hql2);
        session.flush();

        List<AverageSalary_DepartmentName> list =
                session.createQuery("select dep.departmentName as dep_name, avg(emps.salary) as avg_salary, sum (emps.salary) as sum_salary, dep.id as dep_id from Department_Hql dep join dep.listOfEmployees emps " +
                        " group by dep.departmentName "
                , AverageSalary_DepartmentName.class).list();
        list.stream().forEach(result-> log.info("Result: {}", result));
        session.getTransaction().commit();
    }

    @Test
    void checkHql(){
        @Cleanup SessionFactory factory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = factory.openSession();
        Product_Hql product_hql = Product_Hql.builder()
                .name("Apple Iphone 15 Pro")
                .price(4000)
                .build();
        Product_Hql product_hql2 = Product_Hql.builder()
                .name("Samsung S22")
                .price(3400)
                .build();
        session.beginTransaction();
        session.persist(product_hql);
        session.persist(product_hql2);
        session.flush();
//        session.get();
        session.getTransaction().commit();
    }
}
