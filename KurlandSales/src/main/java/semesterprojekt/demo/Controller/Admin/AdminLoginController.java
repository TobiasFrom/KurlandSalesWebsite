package semesterprojekt.demo.Controller.Admin;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class AdminLoginController
{
    private final String ADMIN_LOGIN = "/admin/adminlogin";

    @GetMapping("/adminlogin")
    public String adminLogin()
    {
        log.info("ADMIN_LOGIN action called...");

        return ADMIN_LOGIN;
    }

    @GetMapping("/login-error")
    public String loginError(Model model)
    {
        log.info("ADMIN_LOGIN_ERROR action called...");

        model.addAttribute("loginError", true);

        log.info("ADMIN_LOGIN_ERROR action ended...");

        return ADMIN_LOGIN;
    }
}
