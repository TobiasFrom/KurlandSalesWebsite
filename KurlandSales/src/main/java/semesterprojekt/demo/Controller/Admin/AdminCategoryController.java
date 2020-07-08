package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.ProductService.CategoriesServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminCategoryController
{

    private final String ADMIN_CATEGORY = "/admin/admincategory";
    private final String REDIRECT_ADMIN_CATEGORY = "redirect:/admincategory";

    @Autowired
    ReviewServiceImpl reviewService;

    @Autowired
    CategoriesServiceImpl categoriesService;

    @GetMapping("/admincategory")
    public String adminCategory(Model model)
    {

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("fetchAllCategories", categoriesService.fetchAllCategories());
        model.addAttribute("pc", new ProductCategories());

        return ADMIN_CATEGORY;
    }

    @PostMapping("/uploadcategoryimage")
    public String adminCategory(@RequestParam("fileName") MultipartFile imageFile, @ModelAttribute ProductCategories productCategories) throws Exception
    {

        if(!imageFile.isEmpty())
        {
            categoriesService.savePCImage(productCategories, imageFile);
        }


        return REDIRECT_ADMIN_CATEGORY;
    }

    @GetMapping("delete/specificcategory/{id}")
    public String deleteSpecificCategory(@PathVariable("id") Long id)
    {

        log.info("DELETE_SPECIFIC_CATEGORY action called...");

        if(id != null)
        {
            categoriesService.deleteProductCategory(id);
        }

        log.info("DELETE_SPECIFIC_CATEGORY action ended...");

        return REDIRECT_ADMIN_CATEGORY;
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
