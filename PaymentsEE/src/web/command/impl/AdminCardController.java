package web.command.impl;

import services.CreditCardService;
import services.Impl.CreditCardServiceImpl;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminCardController implements Controller {
    private CreditCardService creditCardService = CreditCardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cards", creditCardService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.ADMIN_CARDS_PATH);
        dispatcher.forward(req, resp);
    }
}
