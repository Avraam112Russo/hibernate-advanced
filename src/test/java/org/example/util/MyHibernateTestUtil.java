package org.example.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class MyHibernateTestUtil {
    public static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:16.2"); // docker image version
    static {
        POSTGRE_SQL_CONTAINER.start();
        // only one container for all tests
    }
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = MyHibernateUtil.buildConfiguration();
        configuration.setProperty("hibernate.connection.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", POSTGRE_SQL_CONTAINER.getUsername());
        configuration.setProperty("hibernate.connection.password", POSTGRE_SQL_CONTAINER.getPassword());
        configuration.configure();
        return configuration.buildSessionFactory();
    }

}
