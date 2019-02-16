package controllers;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojos.Bank;
import pojos.Client;
import pojos.CreditCard;
import pojos.Transaction;
import services.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {


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


    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder encoder;

//    protected  Client client = clientService.findByLogin(getClientFromSession());

//    @RequestMapping(value = "/client")
//    @GetMapping
//    public String showClientInfo(ModelMap modelMap) {
//       fillModel(modelMap);
//       return Paths.CLIENT_PATH;
//    }


    @RequestMapping(value = "/client")
    @GetMapping
    public ModelAndView showClientInfo(Principal principal) {
        Client client = clientService.findByLogin(principal.getName());
        return new ModelAndView(Paths.CLIENT_PATH, "client", client);
    }

    public String getClientFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!( authentication instanceof AnonymousAuthenticationToken )) {
            String currentUserName = authentication.getName();
            return currentUserName;
        } else return "error";
    }

    @RequestMapping(value = "/client_cards")
    @GetMapping
    public ModelAndView showClientCreditCards(Principal principal) {
        Client client = clientService.findByLogin(principal.getName());
        List<CreditCard> creditCardList = creditCardService.getAllClientCards(client.getClientID());
        ModelAndView modelAndView = new ModelAndView(Paths.CLIENT_CARD_PATH, "creditCards", creditCardList);
        return modelAndView;
    }

    @RequestMapping(value = "/client_transactions")
    @GetMapping
    public ModelAndView showClientsTransactions(Principal principal) {
        Client client = clientService.findByLogin(principal.getName());
        List<Transaction> transactionList = transactionService.getAllTransactionsByClientID(client.getClientID());
        return new ModelAndView(Paths.CLIENT_TRANS_PATH, "transactionList", transactionList);
    }

    @RequestMapping(value = "/client_bank_account")
    @GetMapping
    public ModelAndView showClientBankAccountInfo(Principal principal) {
        Client client = clientService.findByLogin(principal.getName());
        Bank bank = bankService.getBankAccountInfoByClientID(client.getClientID());
        if (bank==null){
         bank=new Bank("NEW ACCOUNT",client,0,new ArrayList<>());
         bankService.add(bank);
        }
        return new ModelAndView(Paths.CLIENT_BANK_PATH, "bankAccount", bank);
    }




    @PostMapping(value = "/new_client")
    public String addClient(ModelMap modelMap, @Valid @ModelAttribute("client") Client client, BindingResult result) throws ServletException {

        if (result.hasErrors()) {
            return Paths.REGISTRATION_PATH;
        }

        if (isLoginExists(client.getLogin())) {
            modelMap.addAttribute("errorMessage", "Login already exists, choose another one");
            return Paths.REGISTRATION_PATH;
        }

        client.setPassword(encoder.encode(client.getPassword()));
        client.getClientProfiles().add(clientProfileService.get(1l));
        client = clientService.add(client);
        bankService.add(new Bank("NEW_BANK_ACCOUNT",client,0,new ArrayList<>()));
        modelMap.put("client", clientService.get(client.getClientID()));
        return "redirect:/login";
    }

    @PostMapping(value ="/add_credit_card")
    public String addCreditCard(ModelMap modelMap,@Valid @ModelAttribute("creditCard") CreditCard creditCard, BindingResult result){

        if (result.hasErrors()) {
            return Paths.CLIENT_ADD_CARD_PATH;
        }
        Client client = clientService.findByLogin(getClientFromSession());
        Bank bank = bankService.getBankAccountByCreditCardNumber(creditCard.getCardNumber());
        if(bank!=null){
            creditCard.setBankAccount(bank);
            client.getCards().add(creditCard);
            creditCard.setClient(client);
            clientService.update(client);
//            creditCardService.add(creditCard);
        }
        else {
            creditCard.setBankAccount(bankService.getBankAccountInfoByClientID(client.getClientID()));
            client.getCards().add(creditCard);
            creditCard.setClient(client);
            clientService.update(client);
//            creditCardService.add(creditCard);
        }
        return "redirect:/client_cards";
    }

    private boolean isLoginExists(String login){
        return (clientService.findByLogin(login) != null);
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        model.addAttribute("client", new Client());
        model.addAttribute("clients", clientService.getClients());
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "clients");
    }


}
