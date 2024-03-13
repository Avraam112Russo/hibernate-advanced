package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.mappingEntityAssociations.entity.converter.BirthdayConverter;
import org.example.mappingEntityAssociations.entity.converter.DepartmentConverter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class MyHibernateUtil {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        // we indicated which class hibernate can mapping


//        configuration.addAnnotatedClass(MyEmployee.class);
//        configuration.addAnnotatedClass(MyDepartment.class);
        // names mapping fields object to columns in database
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        // convert Birthday class -> SQL Format
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.addAttributeConverter(new DepartmentConverter());
        configuration.configure();
//        // convert JSONB from SQL to String
//        configuration.registerTypeOverride(new JsonBinaryType());
        return configuration;
    }
}
