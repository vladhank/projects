package web.command.impl;

import entities.Client;
import entities.Transaction;
import services.ClientService;
import services.Impl.ClientServiceImpl;
import services.Impl.TransactionServiceImpl;
import services.TransactionService;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClinetTransactionController implements Controller {
    private TransactionService transactionService = TransactionServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<Transaction> transactions = transactionService.getAllByClientID(client.getClientID());
        req.setAttribute("transactions", transactions);

        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.CLIENT_TRANS_PATH);
        dispatcher.forward(req, resp);
    }
}