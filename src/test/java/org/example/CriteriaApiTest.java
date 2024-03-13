package org.example;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.HQL.Product_Hql;
import org.example.util.MyHibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class CriteriaApiTest {
    @Test
    void checkCriteriaAPI(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

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


        session.beginTransaction();
        session.persist(product_hql);
        session.persist(product_hql2);
        session.persist(product_hql3);
        session.flush();
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Product_Hql> query = criteriaBuilder.createQuery(Product_Hql.class);
        JpaRoot<Product_Hql> from = query.from(Product_Hql.class);
        query.select(from);
        List<Product_Hql> listOfProducts = session.createQuery(query).list();

        log.info("Criteria API -> result: {}", listOfProducts.toString());

        session.getTransaction().commit();
    }
    @Test
    void checkCriteriaAPI_2(){
        @Cleanup SessionFactory sessionFactory = MyHibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

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


        session.beginTransaction();
        session.persist(product_hql);
        session.persist(product_hql2);
        session.persist(product_hql3);
        session.flush();

        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<Product_Hql> query = cb.createQuery(Product_Hql.class);
        JpaRoot<Product_Hql> product = query.from(Product_Hql.class);
        query.select(product).where(cb.equal(product.get("name"), "Samsung S22"));
        Product_Hql product_hql1 = session.createQuery(query).uniqueResult();
        log.info("=============== -> {}", product_hql1);
        session.getTransaction().commit();
    }
}
