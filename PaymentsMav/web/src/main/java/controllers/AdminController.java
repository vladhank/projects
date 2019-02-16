package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojos.Bank;
import pojos.Client;
import pojos.CreditCard;
import pojos.Transaction;
import services.IBankService;
import services.IClientService;
import services.ICreditCardService;
import services.ITransactionService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IClientService clientService;

    @Autowired
    ICreditCardService creditCardService;

    @Autowired
    IBankService bankService;

    @Autowired
    ITransactionService transactionService;

//    @RequestMapping(value = "/admin",method = RequestMethod.GET)
//    public String showAdminPage(){
//        return Paths.ADMIN_PATH;
//    }

    @GetMapping(value ="/admin")
    public ModelAndView showAllClients(){
        List<Client> clientList =clientService.getClients();
        return  new ModelAndView(Paths.ADMIN_PATH,"clients",clientList);
    }

    @GetMapping(value ="/cards")
    public ModelAndView showAllCards(){
        List<CreditCard> creditCardList =creditCardService.getCreditCards();
        return  new ModelAndView(Paths.ADMIN_CARDS_PATH,"cards",creditCardList);
    }



    @GetMapping(value ="/transactions")
    public ModelAndView showAllTransactions(){
        List<Transaction> transactionList =transactionService.getTransactions();
        return  new ModelAndView(Paths.ADMIN_TRANS_PATH,"transactions",transactionList);
    }

    @GetMapping(value ="/bank_accounts")
    public ModelAndView showAllBankAccounts(){
        List<Bank> bankList =bankService.getBankAccounts();
        return  new ModelAndView(Paths.ADMIN_BANK_PATH,"bankAccounts",bankList);
    }

}
