package semesterprojekt.demo.Service.ProductService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.ProductModel;

import java.io.IOException;

@Service
public interface IProductService
{
    ProductModel addProduct(ProductModel productModel);
    ProductModel findProduct(Long id);
    Iterable<ProductModel> fetchAllProducts();
    void deleteProduct(Long id);
    ProductModel saveProductImage(ProductModel productModel, MultipartFile file)throws IOException;
    void editProduct(ProductModel productModel);
    Iterable<ProductModel> searchProducts(String searchAll);

}
