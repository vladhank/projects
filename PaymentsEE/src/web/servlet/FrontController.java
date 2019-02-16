package web.servlet;

import org.apache.log4j.Logger;
import web.command.enums.ControllerType;
import web.command.Paths;
import web.handlers.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = "/frontController")
public class FrontController extends HttpServlet {
    public final static Logger logger = Logger.getLogger(FrontController.class);


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {

       try {

           ControllerType controllerType = RequestHandler.getCommand(req);
           controllerType.getController().execute(req, resp);
       }
       catch (Throwable e){
           resp.sendRedirect(Paths.LOGIN_PATH);
           logger.error("Error " + e);
          e.printStackTrace();
       }
    }
}
