package web.command.impl;

import entities.Client;
import services.ClientService;
import services.Impl.ClientServiceImpl;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientController implements Controller {
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        req.setAttribute("clients", clientService.get(client.getClientID()));
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.CLIENT_PATH);
        dispatcher.forward(req, resp);
    }
}