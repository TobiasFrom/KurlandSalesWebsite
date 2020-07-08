package semesterprojekt.demo.Repo.ProductRepo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Model.ProductModel;
import javax.transaction.Transactional;
import java.util.Optional;


public interface IProductRepo extends CrudRepository<ProductModel, Long>
{

    ProductModel findAllById (Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE ProductModel p SET " +
            "p.name=:name, " +
            "p.price=:price, " +
            "p.shortDescription=:sD, " +
            "p.longDescription=:lD, " +
            "p.productFileName=:pF, " +
            "p.productImage=:img, " +
            "p.productCategories=:pC " +
            "WHERE p.id =:id")
    void updateProductInfoById(
            @Param("name") String name,
            @Param("price") double price,
            @Param("sD") String shortDescription,
            @Param("lD") String longDescription,
            @Param("pF") String productFileName,
            @Param("img") String productImage,
            @Param("pC") ProductCategories pc,
            @Param("id") Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("SELECT p FROM ProductModel p " +
            "WHERE  lower(p.name) LIKE %:search% OR lower(p.longDescription) LIKE %:search% OR lower(p.shortDescription) LIKE %:search% ORDER BY p.name")
    Iterable<ProductModel> searchAll(
            @Param("search") String search);

}
