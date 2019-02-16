package controllers;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojos.Client;
import pojos.CreditCard;

@Controller
public class RegistrationController {

    @GetMapping(value = "/registration")
    public String registerPage(ModelMap modelMap) {
        modelMap.addAttribute("client",new Client());
        return Paths.REGISTRATION_PATH;
    }

    @GetMapping(value = "/add_payments_info")
    public String addCreditCardPage(ModelMap modelMap) {
        modelMap.addAttribute("creditCard",new CreditCard());
        return Paths.CLIENT_ADD_CARD_PATH;
    }
}
