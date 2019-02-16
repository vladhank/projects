package web.command.impl;

import entities.Client;
import services.ClientService;
import services.Impl.ClientServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements Controller {
    ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
        Client client = clientService.getAuthorizationInfo(login);

        if (client != null && login.equals("admin") && password.equals("admin")) {
            req.getSession().setAttribute("admin", "admin");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=admin");

            return;
        }
        if (client != null && password.equals(client.getPassword())) {
            req.getSession().setAttribute("client", client);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=client");
            return;

        }
        else {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            req.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/signin/signin.jsp");
            dispatcher.forward(req, resp);
            return;
        }
    }
}
