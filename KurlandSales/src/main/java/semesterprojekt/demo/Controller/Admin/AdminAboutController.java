package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.AboutModel;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.AboutService.AboutServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;


@Log
@Controller
public class AdminAboutController
{
    private final String ADMIN_FETCH_ABOUT_TEXT = "/admin/adminabout";
    private final String ADMIN_UPDATE_ABOUT = "/admin/adminupdateabout";
    private final String REDIRECT_ADMIN_FETCH_ABOUT_TEXT = "redirect:/adminabout";

    Long tmpId;

    @Autowired
    private AboutServiceImpl aboutService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @GetMapping("/adminabout")
    public String fetchAboutText(Model model)
    {
        log.info("FETCH_ABOUT_TEXT action called...");

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("fetchAboutTextandprofileimage", aboutService.fetchTextAndProfileImage());

        log.info("FETCH_ABOUT_TEXT action ended...");

        return ADMIN_FETCH_ABOUT_TEXT;
    }

    @PostMapping("/saveabouttextandprofiletext")
    public String saveAboutTextAndProfileText(@RequestParam("fileName") MultipartFile imageFile, @RequestParam("heading") String heading)throws Exception
    {
        log.info("SAVE_ABOUT_TEXT action called...");

        if(!imageFile.isEmpty())
        {
            aboutService.saveProfileImage(imageFile, heading);
        }

        log.info("SAVE_ABOUT_TEXT action called...");

        return REDIRECT_ADMIN_FETCH_ABOUT_TEXT;
    }

    @GetMapping("/updateabout/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model)
    {
        log.info("UPDATE_ABOUT_SPECIFIC action called...");

        model.addAttribute("numberOfNotifications", numberOfNotifications());

        tmpId = id;
        AboutModel about = aboutService.findAllById(id);
        model.addAttribute("oldAbout", about);

        log.info("UPDATE_ABOUT_SPECIFIC action ended...");

        return ADMIN_UPDATE_ABOUT;
    }

    @PostMapping("/updateabout")
    public String updateContact(@RequestParam("fileName") MultipartFile imageFile, @RequestParam("heading") String heading)throws Exception
    {
        log.info("UPDATE_ABOUT action called...");

            aboutService.updateAboutSubmit(imageFile, heading, tmpId);

        log.info("UPDATE_ABOUT action ended...");

        return REDIRECT_ADMIN_FETCH_ABOUT_TEXT;
    }

    @GetMapping("delete/specificabout/{id}")
    public String deleteSpecificAbout(@PathVariable("id") Long id)
    {

        log.info("DELETE_SPECIFIC_ABOUT action called...");

        if(id != null)
        {
            aboutService.deleteAbout(id);
        }

        log.info("DELETE_SPECIFIC_CATEGORY action ended...");

        return REDIRECT_ADMIN_FETCH_ABOUT_TEXT;
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
