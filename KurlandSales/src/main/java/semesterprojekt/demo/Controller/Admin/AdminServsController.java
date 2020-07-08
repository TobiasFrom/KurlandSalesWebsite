package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Model.Servs;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;
import semesterprojekt.demo.Service.ServsServiceImpl;


@Log
@Controller
public class AdminServsController
{

    private final String ADMIN_SERVS = "/admin/adminservs";
    private final String ADMIN_SERVS_UPDATE = "/admin/adminservsupdate";
    private final String REDIRECT_ADMIN_SERVS = "redirect:/adminservs";

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private ServsServiceImpl servsService;

    String tmpImg;
    Long teMPId;
    String tempfn;

    @GetMapping("/adminservs")
    public String adminservs(Model model)
    {
        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("servs", servsService.findAll());

        return ADMIN_SERVS;
    }

    @PostMapping("/saveservice")
    public String adminServs(
            @RequestParam("name") String name,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("longDescription") String longDescription,
            @RequestParam("price") String price,
            @RequestParam("fileName") MultipartFile imageFile) throws Exception
                {
                    if(!imageFile.isEmpty())
                    {
                        servsService.saveNewSercs(
                                name,
                                shortDescription,
                                longDescription,
                                price,
                                imageFile);
                    }
                    return REDIRECT_ADMIN_SERVS;
                }

    @GetMapping("/updateservs/{id}")
    public String updateServs(@PathVariable("id") Long id, Model model)
    {

        model.addAttribute("numberOfNotifications", numberOfNotifications());

        teMPId = id;
        tempfn =servsService.findServsById(id).getServsFileName();
        tmpImg = servsService.findServsById(id).getImage();
        Servs servs = servsService.findServsById(id);
        servs.setServsFileName(tempfn);
        servs.setImage(tmpImg);

        model.addAttribute("oldServsmodel",servs);

        //model.addAttribute("servsmodel", new Servs());
        return ADMIN_SERVS_UPDATE;
    }

    @PostMapping("/updateservs")
    public String updateServs(
            @RequestParam("name") String name,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("longDescription") String longDescription,
            @RequestParam("price") String price,
            @RequestParam("fileName") MultipartFile imageFile) throws Exception
    {
        servsService.editServs(name, shortDescription, longDescription, price, imageFile, teMPId);

        return REDIRECT_ADMIN_SERVS;
    }

    @GetMapping("/deleteservs/{id}")
    public String deleteServs(@PathVariable("id")Long id)
    {
        servsService.deleteServs(id);

        return REDIRECT_ADMIN_SERVS;
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
