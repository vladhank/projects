package utils;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = Logger.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public String handle(Exception ex, HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(ex);
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            sb.append(stackTraceElement).append(" \r\n");
        }
        logger.error(sb.toString());

        request.setAttribute("error", "fatal");

        return "index";
    }

    @ExceptionHandler(AccessDeniedException.class)
    //Spring Security will handle with this exception
    public void doNothing(Exception ex, HttpServletRequest request) throws Exception {
        throw ex;
    }
}
