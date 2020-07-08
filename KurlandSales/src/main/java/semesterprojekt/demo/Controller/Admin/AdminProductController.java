package semesterprojekt.demo.Controller.Admin;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Model.ProductModel;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.ProductService.CategoriesServiceImpl;
import semesterprojekt.demo.Service.ProductService.ProductServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminProductController
{

    private final String ADMIN_PRODUCT = "/admin/adminproduct";
    private final String ADMIN_PRODUCT_UPDATE = "/admin/adminupdateproduct";
    private final String REDIRECT_ADMIN_PRODUCT = "redirect:/adminproduct";

    Long tempPId;
    String tmpImg;
    String tempFN;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoriesServiceImpl categoriesService;

    @Autowired
    ReviewServiceImpl reviewService;

    @GetMapping("/adminproduct")
    public String adminProduct(Model model)
    {
        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("fetchAllProducts", productService.fetchAllProducts());
        model.addAttribute("fetchAllCategories", categoriesService.fetchAllCategories());
        model.addAttribute("product", new ProductModel());

        return ADMIN_PRODUCT;
    }

    @PostMapping("/uploadproductimage")
    public String adminProduct(@RequestParam("fileName") MultipartFile imageFile, @ModelAttribute ProductModel productModel) throws Exception
    {

        if(!imageFile.isEmpty())
        {
            productService.saveNewProduct(productModel, imageFile);
        }

        return REDIRECT_ADMIN_PRODUCT;
    }

    @GetMapping("delete/specificproduct/{id}")
    public String deleteSpecificProduct(@PathVariable("id") Long id)
    {

        log.info("DELETE_SPECIFIC_PRODUCT action called...");

        if(id != null)
        {
            productService.deleteProduct(id);
        }

        log.info("DELETE_SPECIFIC_PRODUCT action ended...");

        return REDIRECT_ADMIN_PRODUCT;

    }

    @GetMapping("/updateproduct/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model)
    {
        tempPId = id;
        tempFN = productService.findProduct(id).getProductFileName();
        tmpImg = productService.findProduct(id).getProductImage();
        ProductModel productModel = productService.findProduct(id);
        productModel.setProductFileName(tempFN);
        productModel.setProductImage(tmpImg);

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("fetchAllCategories", categoriesService.fetchAllCategories());
        model.addAttribute("oldProduct", productModel);


        return ADMIN_PRODUCT_UPDATE;
    }

    @PostMapping("/updateproduct")
    public String updateProduct(@RequestParam("fileName") MultipartFile imageFile, @RequestParam("pcid") Long id, ProductModel productModel) throws Exception
    {
        ProductCategories pc = categoriesService.findProductCategory(id);
        if(!imageFile.isEmpty())
        {
            productModel.setProductFileName(null);
            productModel.setProductImage(null);
            productModel.setId(tempPId);
            productModel.setProductCategories(pc);
            productService.saveProductImage(productModel, imageFile);
        }else {
            productModel.setId(tempPId);
            productModel.setProductFileName(tempFN);
            productModel.setProductImage(tmpImg);
            productModel.setProductCategories(pc);
            productService.editProduct(productModel);
        }

        return REDIRECT_ADMIN_PRODUCT;
    }

    public int numberOfNotifications()
    {
        int counter = 0;

        Iterable<Review> reviewList = reviewService.fetchAllReviews();

        for(Review r : reviewList)
        {
            if(!r.getVerified())
            {
                counter++;
            }
        }
        return counter;
    }
}
