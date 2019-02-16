package web.command.impl;

import entities.Client;
import entities.CreditCard;
import org.apache.log4j.Logger;
import services.ClientService;
import services.CreditCardService;
import services.Impl.ClientServiceImpl;
import services.Impl.CreditCardServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClientController implements Controller {
    public final static Logger logger = Logger.getLogger(AddClientController.class);
    ClientService clientService = ClientServiceImpl.getInstance();
    CreditCardService creditCardService = CreditCardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contextPath = req.getContextPath();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String dateOfBirth = req.getParameter("dateOfBirth");
        Date dateClient=new Date(1970/01/17);
//
        String cardCompany = req.getParameter("cardCompany");
        String cardFirstName = req.getParameter("cardFirstName");
        String cardLastName = req.getParameter("cardLastName");
        long cardNumber = Long.parseLong(req.getParameter("cardNumber"));
        int pin = Integer.parseInt(req.getParameter("pin"));
        String expDate = req.getParameter("expDate");
        Date dateCard = new Date(2025/01/17);
        int cvv = Integer.parseInt(req.getParameter("cvv"));
        String status=req.getParameter("status");
        int cashBack = Integer.parseInt(req.getParameter("cashBack"));
        String bankAccount= req.getParameter("bankAccount");
        try {
            dateClient = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
            dateCard = new SimpleDateFormat("MM/yy").parse(expDate);
        } catch (Throwable e) {
            logger.error("Can't parse date" + e);
        }

        if (firstName != null && lastName != null && login != null && password != null && phoneNumber != null & address != null && dateOfBirth != null) {
            clientService.save(new Client(firstName, lastName, phoneNumber, address, dateClient, login, password));
        } else {
            resp.sendRedirect(contextPath + "/frontController");
        }

        if (cardCompany!=null&&cardFirstName!=null&&cardLastName!=null&&cardNumber!=0&&pin!=0&&dateCard!=null&&cvv!=0
                &&status!=null&&cashBack!=0&&bankAccount!=null){
            Client client = clientService.getAuthorizationInfo(login);
            creditCardService.save(new CreditCard(cardNumber,cardCompany,cardFirstName,cardLastName,dateCard,cvv,pin,
                    cashBack,status,bankAccount,client.getClientID() ));
            if(req.getSession().getAttribute("admin").equals("admin")){
                resp.sendRedirect(contextPath + "/frontController?command=admin");

            }
            else{
                resp.sendRedirect(contextPath + "/frontController?command=login");
            }
        }
        else {
            resp.sendRedirect(contextPath + "/frontController");
        }


    }

}
