package semesterprojekt.demo.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Repo.ProductRepo.ICategoriesRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Service
public class CategoriesServiceImpl implements ICategoriesService
{

    @Autowired
    ICategoriesRepo iCategoriesRepo;

    @Override
    public ProductCategories addProductCategory(ProductCategories productCategories)
    {
        return iCategoriesRepo.save(productCategories);
    }

    @Override
    public ProductCategories findProductCategory(Long id)
    {
        return iCategoriesRepo.findAllById(id);
    }

    @Override
    public Iterable<ProductCategories> fetchAllCategories()
    {
        return iCategoriesRepo.findAll();
    }

    @Override
    public void deleteProductCategory(Long id)
    {
         iCategoriesRepo.deleteById(id);
    }


    @Transactional
    public ProductCategories savePCImage(ProductCategories productCategories ,MultipartFile imageFile)throws IOException
    {
        //Converting imageFile into String
        if(!imageFile.isEmpty())
        {
            byte [] byteArr = imageFile.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);

            productCategories.setPcFileName(imageFile.getOriginalFilename());
            productCategories.setCategoryImage(encodedImage);
            iCategoriesRepo.save(productCategories);
        }
        return null;
    }
}
