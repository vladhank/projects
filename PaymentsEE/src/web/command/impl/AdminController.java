package web.command.impl;

import services.ClientService;
import services.Impl.ClientServiceImpl;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController implements Controller {

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clientService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.ADMIN_PATH);
        dispatcher.forward(req, resp);
    }
}
