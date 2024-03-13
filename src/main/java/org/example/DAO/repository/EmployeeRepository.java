package org.example.DAO.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.example.DAO.entity.MyEmployee;
import org.hibernate.SessionFactory;

@Slf4j
public class EmployeeRepository extends RepositoryBase<Long, MyEmployee> {

    public EmployeeRepository(EntityManager entityManager) {
        super(entityManager, MyEmployee.class);
    }
}
