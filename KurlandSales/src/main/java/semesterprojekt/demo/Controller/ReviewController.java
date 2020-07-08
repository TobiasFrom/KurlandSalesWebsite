package semesterprojekt.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import semesterprojekt.demo.Model.NavigationBar;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.NavigationBar.INavBarService;
import semesterprojekt.demo.Service.NavigationBar.NavBarServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Controller
public class ReviewController
{

    @Autowired
    NavBarServiceImpl navBarService;

    @Autowired
    ReviewServiceImpl reviewService;

    private final String REVIEW = "/review";
    private final String REDIRECT_REVIEW = "redirect:/review";


    @GetMapping("/review")
    public String review(Model model)
    {
        model.addAttribute("navigationBar", navBarService.fetchAllNames());
        model.addAttribute("reviews", reviewService.fetchAllReviews());
        model.addAttribute("newReview", new Review());

        return REVIEW;
    }

    @PostMapping("/review")
    public String review(@ModelAttribute Review review)
    {

        reviewService.addReview(review);

        return REDIRECT_REVIEW;
    }
}
