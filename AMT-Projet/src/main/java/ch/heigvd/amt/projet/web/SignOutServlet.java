package ch.heigvd.amt.projet.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/signout", name = "SignOutServlet")
public class SignOutServlet extends HttpServlet {

    /**
     * Method GET to logout the user and redirect to the signin page
     * @param request http request
     * @param response http response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //We invalidate the previous session
        request.getSession().invalidate();

        //We redirect to the signin page
        response.sendRedirect(request.getContextPath() + "/signin");
    }
}
