package semesterprojekt.demo.Model;

import lombok.*;

import javax.persistence.*;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCategories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String pcFileName;

    @Type(type="text")
    private String categoryImage;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategories")
    private Set<ProductModel> productModels = new HashSet<>();

    public ProductCategories(String name, String productImage, Set<ProductModel> productModels) {
        this.name = name;
        this.categoryImage = productImage;
        this.productModels = productModels;
    }

    public ProductCategories(String name, Set<ProductModel> productModels) {
        this.name = name;
        this.productModels = productModels;
    }

    public ProductCategories(String name) {
        this.name = name;
    }

    public ProductCategories(String name, String productImage) {
        this.name = name;
        this.categoryImage = productImage;
    }

    @Override
    public String toString() {
        return "ProductCategories{" +
                " productModels=" + productModels +
                '}';
    }
}
