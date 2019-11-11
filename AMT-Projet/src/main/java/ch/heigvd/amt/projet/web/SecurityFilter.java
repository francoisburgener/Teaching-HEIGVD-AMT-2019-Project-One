package ch.heigvd.amt.projet.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class to filtre all path (/home/*) to check if the user is authentifiate
 */
@WebFilter(urlPatterns = "/home/*",filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        //If the session is null of the attribute userSession don't exist
        if(session == null || session.getAttribute("userSession") == null){

            //Redirect to the sign in page
            response.sendRedirect(request.getContextPath() + "/signin");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
