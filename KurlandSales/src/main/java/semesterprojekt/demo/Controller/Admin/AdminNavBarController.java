package semesterprojekt.demo.Controller.Admin;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import semesterprojekt.demo.Model.NavigationBar;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.NavigationBar.NavBarServiceImpl;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminNavBarController
{

    private final String ADMIN_NAVIGATION_BAR = "/admin/adminnavigationbar";
    private final String ADMIN_NAVBAR_UPDATE = "/admin/adminupdatenavbar";
    private final String REDIRECT_ADMIN_NAVBAR = "redirect:/adminnavigationbar";

    @Autowired
    private NavBarServiceImpl navBarService;

    @Autowired
    ReviewServiceImpl reviewService;

    Long tempNBId;

    @GetMapping("/adminnavigationbar")
    public String adminNavigationBar(Model model)
    {
        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("navigationBar", navBarService.fetchAllNames());

        return ADMIN_NAVIGATION_BAR;
    }


    @GetMapping("/updatenavbar/{id}")
    public String updateNavBar(@PathVariable("id") Long id, Model model)
    {
        tempNBId = id;
        NavigationBar navigationBar = navBarService.findNaviBarById(tempNBId);

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("navbar", navigationBar);

        return ADMIN_NAVBAR_UPDATE;
    }

    @PostMapping("/updatenavbar")
    public String updateNavBar(NavigationBar nb)
    {
        nb.setId(tempNBId);
        navBarService.editNavBar(nb);


        return REDIRECT_ADMIN_NAVBAR;
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
