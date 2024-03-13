package org.example;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
//import org.example.TABLE_PER_CLASS.Language;
//import org.example.TABLE_PER_CLASS.Manager;
//import org.example.TABLE_PER_CLASS.Programmer;
//import org.example.TABLE_PER_CLASS.User;
import org.example.inheritance.SINGLE_TABLE.Manager;
import org.example.inheritance.SINGLE_TABLE.Programmer;
import org.example.inheritance.SINGLE_TABLE.User;
import org.example.inheritance.joined.MyManager;
import org.example.inheritance.joined.MyProgrammer;
import org.example.inheritance.mappedSuperClass.GenreTypeMusic;
import org.example.inheritance.mappedSuperClass.Music;
import org.example.mappingEntityAssociations.entityOneToMany.ProductType;
import org.example.mappingEntityAssociations.orderBy.MyDepartment;
import org.example.mappingEntityAssociations.orderBy.MyEmployee;
import org.example.util.MyHibernateTestUtil;
import org.example.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;


@Slf4j
class HibernateRunnerTest {

    @Test
    void checkReflectionApi() throws SQLException, IllegalAccessException {
//        UserEntity user = UserEntity.builder()
//                .userName("Masha01")
//                .firstName("updateName")
//                .lastName("Sokolova")
//                .birthDay(LocalDate.of(2000, 8, 16))
//                .age(23)
//                .build();
//
//
//
//        String sql = """
//                insert
//                into %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//        Field[] declaredFields = user.getClass().getDeclaredFields();
//        String columnNames = Arrays.stream(declaredFields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(column -> column.name()).orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//        String columnValues = Arrays.stream(declaredFields)
//                .map(field -> "?").collect(Collectors.joining(", "));
//        System.out.println(sql.formatted(tableName, columnNames, columnValues));
//        Connection connection = null;
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//        for (Field field:declaredFields){
//            field.setAccessible(true);
//            preparedStatement.setObject(1, field.get(user));
//        }
    }

    @Test
    void getProductTypeTest(){
        @Cleanup SessionFactory sessionFactory = MyHibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        ProductType productType = session.get(ProductType.class, 1);
        log.info("Fetch product type: {}", productType);
        log.info("Fetch products: {}", productType.getListOfProducts());
    }
    @Test
    void saveMyDepartmentTest(){
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(MyDepartment.class);
            configuration.addAnnotatedClass(MyEmployee.class);
            configuration.configure();
            //create sessionFactory with testContainer
            @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
            @Cleanup Session session = sessionFactory.openSession();

            session.beginTransaction();

            MyDepartment department = MyDepartment.builder()
                    .name("PR")
                    .build();
            MyEmployee employee = MyEmployee.builder()
                    .name("Russo")
                    .build();
            department.addEmployeeToDepartment(employee);
            session.persist(department);


            log.info("================================================");
            log.info("Saved department: {}", session.get(MyDepartment.class, department.getId()).toString());
            log.info("Saved employee: {}", session.get(MyEmployee.class, employee.getId()));
            Assertions.assertEquals(1, session.get(MyDepartment.class, department.getId()).getId());
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred: ", exception);
        }
    }
    @Test
    void check_Mapped_Super_Class(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Music music = Music.builder()
                .name("I have a dread somebody like you")
                .build();
        Music music_2 = Music.builder()
                .name("Don't worry be happy")
                .build();
        GenreTypeMusic typeMusic = GenreTypeMusic.builder()
                .name("Dram")
                .build();
        typeMusic.addMusicToList(music);
        typeMusic.addMusicToList(music_2);
        session.persist(typeMusic);

        log.info("Genre: {}", session.get(GenreTypeMusic.class, typeMusic.getId()).toString());
        log.info("Music: {}",session.get(GenreTypeMusic.class, typeMusic.getId()).getListOfMusic().toString());
        session.getTransaction().commit();
    }
//    @Test
//    void check_Table_Per_Class_Test(){
//        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Programmer programmer = Programmer.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("john@gmail.com")
//                .language(Language.JAVA)
//                .build();
//        Manager manager = Manager.builder()
//                .firstName("Sasha")
//                .lastName("Petrov")
//                .email("sasha@gmail.com")
//                .project("IT_EXTRA")
//                .build();
//        session.persist(programmer);
//        session.persist(manager);
//        session.flush();
//        User user = session.get(User.class, 1); // all union table
//        User user2 = session.get(User.class, 2); // all union table
//        // one generate sequence for two tables
//        log.info("User: {}", user);
//        log.info("User: {}", user2);
//        session.getTransaction().commit();
//    }
    @Test
    void check_Single_Table_Test(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        User programmer = Programmer.builder()
                .name("John")
                .lastName("Doe")
                .language("JAVA")
                .build();
        User manager = Manager.builder()
                .name("Sasha")
                .lastName("Petrov")
                .project("IT_EXTRA")
                .build();
        session.persist(programmer);
        session.persist(manager);
//        // one generate sequence for two tables
        log.info("User: {}", programmer.toString());
        log.info("User: {}", manager.toString());
        session.getTransaction().commit();
    }

    @Test
    void check_Joined_columns_inheritance(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        MyProgrammer programmer = MyProgrammer.builder()
                .name("John")
                .lastName("Doe")
                .language("JAVA")
                .build();
        MyManager manager = MyManager.builder()
                .name("Sasha")
                .lastName("Petrov")
                .project("IT_EXTRA")
                .build();
        session.persist(programmer);
        session.persist(manager);
        session.flush();

        log.info("User: {}", session.get(MyProgrammer.class, programmer.getId()));
        log.info("User: {}", session.get(MyManager.class, manager.getId()));
        session.getTransaction().commit();

        @Cleanup SessionFactory sessionFactory2 = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        log.info("User: {}", session2.get(MyProgrammer.class, programmer.getId()));
        log.info("User: {}", session2.get(MyManager.class, manager.getId()));
        session2.getTransaction().commit();
    }





}