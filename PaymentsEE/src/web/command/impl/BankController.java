package web.command.impl;

import services.BankService;
import services.Impl.BankServiceImpl;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BankController implements Controller {
    private BankService bankService = BankServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bank", bankService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.ADMIN_BANK_PATH);
        dispatcher.forward(req, resp);
    }
}