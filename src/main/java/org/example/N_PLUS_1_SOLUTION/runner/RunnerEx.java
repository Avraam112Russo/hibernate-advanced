package org.example.N_PLUS_1_SOLUTION.runner;

import lombok.Cleanup;
import org.example.util.MyHibernateUtil;
import org.example.N_PLUS_1_SOLUTION.entity.Chat;
import org.example.N_PLUS_1_SOLUTION.entity.EmployeeChats;
import org.example.N_PLUS_1_SOLUTION.entity.MyDepartment2;
import org.example.N_PLUS_1_SOLUTION.entity.MyEmployee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RunnerEx {
    public static void main(String[] args) {

insertEntities();
    }

    static void checkEntityGraph(){
        @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
//        Map<String, Object> properties = Map.of(
//                GraphSemantic.FETCH.getJakartaHintName(), session.getEntityGraph("withDepartment")
//        );
//        MyEmployee myEmployee = session.find(MyEmployee.class, 1, properties);
//        System.out.println("result: " + myEmployee);

        MyEmployee myEmployee = session.get(MyEmployee.class, 1);
        session.get(MyDepartment2.class, myEmployee.getDepartment().getId());
        session.getTransaction().commit();
    }
    public static void selectData(){
        try (SessionFactory factory = MyHibernateUtil.buildSessionFactory();
             Session session = factory.openSession()
        ){
            session.beginTransaction();
            List<MyDepartment2> list = session.createQuery("select dep from MyDepartment2 dep join fetch dep.listOfEmployees", MyDepartment2.class).list();
            for (MyDepartment2 dep : list){
                System.out.println(dep.toString());
            }
            session.getTransaction().commit();
        }
    }
    static void check_FetchProfile(){
        try (SessionFactory factory = MyHibernateUtil.buildSessionFactory();
             Session session = factory.openSession()
        ){
            session.beginTransaction();
//            session.enableFetchProfile("someNameFetchProfile");
            session.createQuery("select emps from EmployeeQueryCache emps", MyEmployee.class).list().stream().forEach(e -> System.out.println(e));
            session.getTransaction().commit();
        }
    }

    static void insertEntities(){
        MyDepartment2 department = MyDepartment2
                .builder()
                .departmentName("IT")
                .build();
        MyEmployee employee = MyEmployee.builder()
                .userName("avraam@russo")
                .build();
        MyEmployee employee_2 = MyEmployee.builder()
                .userName("elena@russo")
                .build();
//
//        MyDepartment2 department_2 = MyDepartment2.
//                builder()
//                .departmentName("SALES")
//                .build();
//        MyEmployee employee_3 = MyEmployee.builder()
//                .userName("sashaPetrov")
//                .build();
//        MyEmployee employee_4 = MyEmployee.builder()
//                .userName("maxIvanov")
//                .build();


        department.addEmployeeToDepartment(employee);
        department.addEmployeeToDepartment(employee_2);

//        department_2.addEmployeeToDepartment(employee_3);
//        department_2.addEmployeeToDepartment(employee_4);

        Chat chat = Chat.builder()
                .chatName("IT DISCUSS")
                .build();
//        Chat chat_2 = Chat.builder()
//                .chatName("SALES DISCUSS")
//                .build();
        EmployeeChats employeeChats = EmployeeChats.builder().build();
        EmployeeChats employeeChats2 = EmployeeChats.builder().build();
//        EmployeeChats employeeChats3 = EmployeeChats.builder().build();


        employeeChats.addEmployeeToChatAndChatToEmployee(chat, employee);
//        employeeChats.addEmployeeToChatAndChatToEmployee(chat_2, employee);
        employeeChats2.addEmployeeToChatAndChatToEmployee(chat, employee_2);
//        employeeChats3.addEmployeeToChatAndChatToEmployee(chat_2, employee_3);
//        employeeChats3.addEmployeeToChatAndChatToEmployee(chat_2, employee_4);
        try(SessionFactory factory = MyHibernateUtil.buildSessionFactory();
            Session session = factory.openSession();
        ) {
            session.beginTransaction();
            session.persist(department);
//            session.persist(department_2);
            session.persist(employeeChats);
            session.persist(employeeChats2);
//            session.persist(employeeChats3);
            session.getTransaction().commit();
            session.clear();
        }
    }
}
