package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojos.Client;
import services.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SendMoneyController {

    @Autowired
    IClientService clientService;

    @Autowired
    ITransactionService transactionService;

    @Autowired
    IClientProfileService clientProfileService;

    @Autowired
    ICreditCardService creditCardService;

    @Autowired
    IBankService bankService;


    @GetMapping(value = "/send_money_page")
    public ModelAndView showSendMoneyPage(Principal principal) {
        Client client = clientService.findByLogin(principal.getName());
        Double balance = bankService.getBalanceByClientID(client.getClientID());
        return new ModelAndView(Paths.SEND_MONEY_PATH, "balance", balance);
    }

    @PostMapping(value = "/send_money")
    public ModelAndView sendMoney(ModelMap modelMap,Principal principal, @Valid Long cardNumber, @Valid Double moneyAmount) {
        String result;
        Client client = clientService.findByLogin(principal.getName());

//        if (result.hasErrors()) {
//            return "error";
//        }

        if(creditCardService.sendMoney(moneyAmount,client.getClientID(),cardNumber)){
           result = "Success transaction";
            return new ModelAndView(Paths.CLIENT_TRANS_PATH, "transactionResult", result);
        }
        result="Ooops....Something went wrong! Try again ";
        return new ModelAndView(Paths.CLIENT_TRANS_PATH, "transactionResult", result);
    }
}
