package semesterprojekt.demo.Service.ProductService;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Model.ProductModel;
import semesterprojekt.demo.Repo.ProductRepo.IProductRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Log
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    IProductRepo iProductRepo;

    @Override
    public ProductModel addProduct(ProductModel product)
    {
        return iProductRepo.save(product);
    }

    @Override
    public ProductModel findProduct(Long id)
    {
        return iProductRepo.findAllById(id);
    }

    @Override
    public Iterable<ProductModel> fetchAllProducts()
    {
        return iProductRepo.findAll();
    }

    @Override
    public void deleteProduct(Long id)
    {
        iProductRepo.deleteById(id);
    }

    @Override
    public void editProduct(ProductModel productModel)
    {
        Long id = productModel.getId();
        String name = productModel.getName();
        double price = productModel.getPrice();
        String shortDescription = productModel.getShortDescription();
        String longDescription = productModel.getLongDescription();
        String productFileName = productModel.getProductFileName();
        String productImage = productModel.getProductImage();
        ProductCategories pc = productModel.getProductCategories();

        iProductRepo.updateProductInfoById(name, price, shortDescription, longDescription, productFileName, productImage, pc, id);

    }

    @Transactional
    public ProductModel saveProductImage(ProductModel productModel, MultipartFile imageFile)throws IOException
    {
        if(!imageFile.isEmpty())
        {
            saveImages(productModel, imageFile);
            editProduct(productModel);
        }
        return null;
    }

    @Transactional
    public ProductModel saveNewProduct(ProductModel productModel, MultipartFile imageFile)throws IOException
    {
        if(!imageFile.isEmpty())
        {
            saveImages(productModel, imageFile);
            iProductRepo.save(productModel);
        }
        return null;
    }


    private void saveImages(ProductModel productModel, MultipartFile imageFile) throws IOException {

        //Converting imageFile into String
        byte [] byteArr = imageFile.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);

        productModel.setProductFileName(imageFile.getOriginalFilename());
        productModel.setProductImage(encodedImage);
    }

    public Iterable<ProductModel> searchProducts(String searchAll)
    {
        try
        {
            if(!searchAll.equals(""))
            {
                return iProductRepo.searchAll(searchAll.toLowerCase());
            }
        } catch (Exception a)
        {
            log.info(String.valueOf(a));
        }

        return null;
    }
}
