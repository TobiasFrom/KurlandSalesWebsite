package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.NavigationBar.NavBarServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminReviewController
{
    private final String ADMIN_REVIEW = "/admin/adminreview";
    private final String REDIRECT_ADMIN_REVIEW = "redirect:/adminreview";


    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private NavBarServiceImpl navBarService;

    @GetMapping("/adminreview")
    public String adminReview(Model model)
    {
        log.info("ADMIN_VERIFY action called...");

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("navigationBar", navBarService.fetchAllNames());
        model.addAttribute("reviews", reviewService.fetchAllReviews());

        log.info("ADMIN_VERIFY action ended...");

        return ADMIN_REVIEW;
    }

    @GetMapping("verify/specificreview/{id}")
    public String verifyReview(@PathVariable("id") Long id)
    {
        log.info("VERIFY_SPECIFIC_REVIEW action called...");
        //Review review = reviewService.findReviewById(id);
        if(id != null)
        {
            reviewService.verifyReview(reviewService.findReviewById(id));
        }

        log.info("VERIFY_SPECIFIC_REVIEW action ended...");


        return REDIRECT_ADMIN_REVIEW;
    }

    @GetMapping("delete/specificreview/{id}")
    public String deleteSpecificReview(@PathVariable("id") Long id)
    {

        log.info("DELETE_SPECIFIC_REVIEW action called...");

        if(id != null)
        {
            reviewService.deleteReview(id);
        }

        log.info("DELETE_SPECIFIC_REVIEW action ended...");

        return REDIRECT_ADMIN_REVIEW;

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
