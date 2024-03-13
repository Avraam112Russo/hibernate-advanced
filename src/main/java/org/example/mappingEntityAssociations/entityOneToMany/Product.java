package org.example.mappingEntityAssociations.entityOneToMany;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_products")
@EqualsAndHashCode(of = "productName") // only this field
@ToString(exclude = "productType")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name", nullable = true, unique = true)
    private String productName;
    @Column(name = "price")
    private Integer price;
    @JoinColumn(name = "product_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductType productType;
}
