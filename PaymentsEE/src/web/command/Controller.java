package web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String MAIN_PAGE ="view/main.jsp";
    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
