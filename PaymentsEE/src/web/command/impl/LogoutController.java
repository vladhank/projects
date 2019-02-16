package web.command.impl;

import web.command.Controller;
import web.command.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController implements Controller {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getServletContext().getRequestDispatcher(Paths.LOGIN_PATH).forward(req, resp);
    }
}
