package org.example.mappingEntityAssociations.entityOneToMany;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_types")
@Data
@AllArgsConstructor
// HashSet call equalsAndHasCode -> Enter in product field -> again call ProductType -> again HashSet call equalsAndHasCode -> stackOverFlow
@EqualsAndHashCode(of = "productTypeName") // only this field
@NoArgsConstructor
@Builder
@ToString(exclude = "listOfProducts")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    private Integer productTypeId;
    @Column(name = "product_type_name")
    private String productTypeName;
    @OneToMany(
            mappedBy = "productType",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true // deleted product from database if we remove him from our collection listOfProducts
    )
    @Builder.Default
    private Set<Product> listOfProducts = new HashSet<>();

    public void addToProductTypeList(Product product){
        listOfProducts.add(product);
        product.setProductType(this);
    }
}
