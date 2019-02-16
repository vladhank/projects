package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojos.Client;
import services.IClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class RootController {

    @Autowired
    IClientService clientService;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @GetMapping
    @RequestMapping(value = "/login")
    public String loginPage(ModelMap model) {
        fillModel(model);
        return Paths.LOGIN_PATH;
    }

//    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @GetMapping
    @RequestMapping(value = "/main")
    public String showMain() {
        return Paths.MAIN;
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        fillModel(model);
        model.addAttribute("user", getPrincipal());
        return "access_denied";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private void fillModel(ModelMap model) {
        model.addAttribute("client", new Client());
        model.addAttribute("client", clientService.getClients());
    }


}
