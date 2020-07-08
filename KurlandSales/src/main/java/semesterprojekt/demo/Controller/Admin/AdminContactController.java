package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import semesterprojekt.demo.Model.Contact;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Service.IContactService;
import semesterprojekt.demo.Service.ReviewService.ReviewServiceImpl;

@Log
@Controller
public class AdminContactController
{

    private final String ADMIN_CONTACT = "/admin/admincontact";
    private final String ADMIN_CONTACT_UPDATE = "/admin/adminupdatecontact";
    private final String REDIRECT_ADMIN_CONTACT = "redirect:/admincontact";

    Long tmpId;

    @Autowired
    ReviewServiceImpl reviewService;

    @Autowired
    private IContactService contactService;

    @GetMapping("/updatecontact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model)
    {
        tmpId = id;
        Contact contact = contactService.findContactById(id);
        System.out.println(contact);

        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("contact", contact);

        return ADMIN_CONTACT_UPDATE;
    }

    @PostMapping("/updatecontact")
    public String updateContact(Contact k)
    {
        log.info("UPDATE_CONTACT action called...");

        k.setId(tmpId);
        contactService.updateContact(k);

        log.info("UPDATE_CONTACT action ended...");

        return REDIRECT_ADMIN_CONTACT;
    }

    @GetMapping("/admincontact")
    public String adminContact(Model model)
    {
        model.addAttribute("numberOfNotifications", numberOfNotifications());
        model.addAttribute("contact", contactService.findAll());
        log.info("ADMIN_CONTACT action called...");
        return ADMIN_CONTACT;
    }

    @PostMapping("/createcontact")
    public String createContact(Contact contact)
    {
        contactService.addContact(contact);

        return REDIRECT_ADMIN_CONTACT;
    }
    @GetMapping("/deletecontact/{id}")
    public String deleteContact(@PathVariable("id") Long id)
    {
        contactService.deleteContact(id);

        return REDIRECT_ADMIN_CONTACT;
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
