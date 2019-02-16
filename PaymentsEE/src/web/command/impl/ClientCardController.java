package web.command.impl;

import entities.Client;
import entities.CreditCard;
import services.ClientService;
import services.CreditCardService;
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

public class ClientCardController implements Controller {
    private CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<CreditCard> cards = creditCardService.getCardsByID(client.getClientID());
        req.setAttribute("cards", cards);

        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.CLIENT_CARD_PATH);
        dispatcher.forward(req, resp);
    }
}