package semesterprojekt.demo.Model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String shortDescription;

    private String longDescription;

    private String productFileName;

    @Type(type="text")
    private String productImage;

    @ManyToOne()
    private ProductCategories productCategories;

    public ProductModel(String name, double price, String shortDescription, String longDescription, String productImage, ProductCategories productCategories)
    {
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.productImage = productImage;
        this.productCategories = productCategories;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                " productCategories=" + productCategories +
                '}';
    }
}
