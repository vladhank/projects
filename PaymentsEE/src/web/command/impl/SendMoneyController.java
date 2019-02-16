package web.command.impl;

import entities.Bank;
import entities.Client;
import entities.CreditCard;
import org.apache.log4j.Logger;
import services.BankService;
import services.ClientService;
import services.CreditCardService;
import services.Impl.BankServiceImpl;
import services.Impl.ClientServiceImpl;
import services.Impl.CreditCardServiceImpl;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SendMoneyController implements Controller {
    public final static Logger logger = Logger.getLogger(SendMoneyController.class);
    ClientService clientService = ClientServiceImpl.getInstance();
    CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
    BankService bankService = BankServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.SEND_MONEY_PATH);
        dispatcher.forward(req, resp);
        String contextPath = req.getContextPath();
        Client client = (Client) req.getSession().getAttribute("client");
        List<CreditCard> cards = creditCardService.getCardsByID(client.getClientID());
        Bank bank = bankService.get(cards.get(0).getCardNumber());
        long cardNumberReceiver = Long.parseLong(req.getParameter("cardNumber"));
        double amount = Double.parseDouble(req.getParameter("moneyAmount"));
//        creditCardService.sendMoney(amount,cards.get(0).getCardNumber(),cardNumberReceiver);
        if (creditCardService.sendMoney(amount, cards.get(0).getCardNumber(), cardNumberReceiver)) {
            resp.sendRedirect(contextPath + "/frontController?command=client");
        }
    }

}

