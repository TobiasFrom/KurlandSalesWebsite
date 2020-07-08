package semesterprojekt.demo.Repo.ProductRepo;

import org.springframework.data.repository.CrudRepository;
import semesterprojekt.demo.Model.ProductCategories;

public interface ICategoriesRepo extends CrudRepository<ProductCategories, Long>
{


    ProductCategories findAllById(Long id);

//    @Query("SELECT pc FROM ProductCategories pc WHERE pc.id = :id")
//    List<ProductModel> findProductsByCategories(Long id);

}
