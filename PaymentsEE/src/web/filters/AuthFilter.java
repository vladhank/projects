package web.filters;

import web.command.enums.ControllerType;
import web.handlers.RequestHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.command.enums.ControllerType.ADMIN;

@WebFilter(urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        ControllerType type = RequestHandler.getCommand(req);
        if (ADMIN.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if (( session.getAttribute("admin") == null )) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}