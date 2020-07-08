package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.*;
import semesterprojekt.demo.Service.NewsServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminHomeController
{
    private final String ADMIN_MENU = "/admin/admin";
    private final String REDIRECT_ADMIN_MENU= "redirect:/admin";

    @Autowired
    private NewsServiceImpl newsServiceImpl;

    @Autowired
    ReviewServiceImpl reviewService;


    @GetMapping("/admin")
    public String adminMenu(Model model)
    {
        log.info("ADMIN_MENU action called...");

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("fetchAllNews", newsServiceImpl.fetchAllNews());

        log.info("ADMIN_MENU action ended...");

        return ADMIN_MENU;
    }

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("fileName") MultipartFile imageFile) throws Exception
    {

        if(!imageFile.isEmpty())
        {
            newsServiceImpl.saveImage(imageFile);
        }

        return REDIRECT_ADMIN_MENU;
    }

    @GetMapping("delete/specificnews/{id}")
    public String deleteSpecificNews(@PathVariable("id") Long id)
    {

        log.info("deleteSpecificNews action called...");

        if(id != null)
        {
            newsServiceImpl.deleteSpecificNews(id);
        }

        log.info("deleteSpecific action ended...");

        return REDIRECT_ADMIN_MENU;

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