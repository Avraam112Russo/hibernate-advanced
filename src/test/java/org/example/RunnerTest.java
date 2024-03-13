package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.N_PLUS_1_SOLUTION.entity.Chat;
import org.example.N_PLUS_1_SOLUTION.entity.EmployeeChats;
import org.example.N_PLUS_1_SOLUTION.entity.MyDepartment2;
import org.example.N_PLUS_1_SOLUTION.entity.MyEmployee;
import org.example.util.MyHibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class RunnerTest {
    @Test
    void check(){
        MyDepartment2 department = MyDepartment2.builder()
                .departmentName("SALES")
                .build();
        MyEmployee employee = MyEmployee.builder()
                .userName("avraam@russo")
                .build();
        MyEmployee employee_2 = MyEmployee.builder()
                .userName("elena@russo")
                .build();
        department.addEmployeeToDepartment(employee_2);
        department.addEmployeeToDepartment(employee);

        try (SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
        ){

            session.beginTransaction();
            session.persist(department);
            session.flush();
            MyDepartment2 savedDep = session.get(MyDepartment2.class, department.getId());
            log.info(savedDep.toString());
            log.info(savedDep.getListOfEmployees().toString());
            session.getTransaction().commit();
        }
    }
    @Test
    void checkManyToManyAssociations(){
        MyDepartment2 department = MyDepartment2.builder()
                .departmentName("SALES")
                .build();
        MyEmployee employee = MyEmployee.builder()
                .userName("avraam@russo")
                .build();
        MyEmployee employee_2 = MyEmployee.builder()
                .userName("elena@russo")
                .build();
        department.addEmployeeToDepartment(employee);
        department.addEmployeeToDepartment(employee_2);

        Chat chat = Chat.builder()
                .chatName("IT DISCUSS")
                .build();
        EmployeeChats employeeChats = EmployeeChats.builder().build();
        EmployeeChats employeeChats2 = EmployeeChats.builder().build();
         employeeChats.addEmployeeToChatAndChatToEmployee(chat, employee);
         employeeChats2.addEmployeeToChatAndChatToEmployee(chat, employee_2);

         try (SessionFactory factory = MyHibernateTestUtil.buildSessionFactory();
                Session session = factory.openSession();
         ){
             session.beginTransaction();
             session.persist(department);
             session.persist(employeeChats);
             session.persist(employeeChats2);
             session.flush();

             MyDepartment2 savedDepartment = session.get(MyDepartment2.class, department.getId());
             log.info("Department name: {} \n List of employees: {} ",
                     savedDepartment.getDepartmentName(),
                     savedDepartment.getListOfEmployees().toString());
             EmployeeChats savedEmpChats = session.get(EmployeeChats.class, employeeChats.getId());
             EmployeeChats savedEmpChats2 = session.get(EmployeeChats.class, employeeChats2.getId());
             log.info("All_Employee and chats: {}", savedEmpChats.toString());
             log.info("All_Employee and chats: {}", savedEmpChats2.toString());

             Chat savedChat = session.get(Chat.class, chat.getId());
             List<EmployeeChats> listOfEmployeesChats = savedChat.getListOfEmployeesChats();
             for (EmployeeChats employeeChats1 : listOfEmployeesChats){
                 log.info("RESULT: {}", employeeChats1.toString());
             }
             session.getTransaction().commit();
         } catch (Exception exception){
             log.error("Error occurred: ", exception);
         }
    }
    @Test
    void check2(){
        MyDepartment2 department = MyDepartment2.builder()
                .departmentName("SALES")
                .build();
        MyEmployee employee = MyEmployee.builder()
                .userName("avraam@russo")
                .build();
        MyEmployee employee_2 = MyEmployee.builder()
                .userName("elena@russo")
                .build();
        department.addEmployeeToDepartment(employee);
        department.addEmployeeToDepartment(employee_2);

        Chat chat = Chat.builder()
                .chatName("IT DISCUSS")
                .build();
        EmployeeChats employeeChats = EmployeeChats.builder().build();
        EmployeeChats employeeChats2 = EmployeeChats.builder().build();
        employeeChats.addEmployeeToChatAndChatToEmployee(chat, employee);
        employeeChats2.addEmployeeToChatAndChatToEmployee(chat, employee_2);
        try(SessionFactory factory = MyHibernateTestUtil.buildSessionFactory();
            Session session = factory.openSession();
        ) {
            session.beginTransaction();
            session.persist(department);
            session.persist(employeeChats);
            session.persist(employeeChats2);
            session.getTransaction().commit();
        }
    }
}
