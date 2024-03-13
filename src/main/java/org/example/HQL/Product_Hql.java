package org.example.HQL;

import jakarta.persistence.*;
import lombok.*;

@NamedQuery(name = "findAllProducts", query = "select prod from Product_Hql prod order by prod.price desc") // get all products
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_product")
@Entity
@Builder
@ToString
public class Product_Hql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
}
