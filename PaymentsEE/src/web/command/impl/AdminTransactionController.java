package web.command.impl;

import services.Impl.TransactionServiceImpl;
import services.TransactionService;
import web.command.Controller;
import web.command.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminTransactionController implements Controller {
    private TransactionService transactionService = TransactionServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("transactions", transactionService.getAll() );
        RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.ADMIN_TRANS_PATH);
        dispatcher.forward(req, resp);
    }
}

