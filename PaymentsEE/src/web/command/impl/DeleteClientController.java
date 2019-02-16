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

public class DeleteClientController implements Controller {
    private ClientService clientService = ClientServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientID = Long.parseLong(req.getParameter("cliientid"));
        System.out.println(req.getParameter("clientid"));
        if(clientID!=0){
            clientService.delete(clientID);
            RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.ADMIN_PATH);
            dispatcher.forward(req, resp);
        }
        else{
            RequestDispatcher dispatcher = req.getRequestDispatcher(Paths.LOGIN_PATH);
            dispatcher.forward(req, resp);
        }
    }

}
