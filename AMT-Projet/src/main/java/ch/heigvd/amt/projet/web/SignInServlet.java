package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/signin", name = "SignInServlet")
public class SignInServlet extends HttpServlet {


    @EJB
    UsersManagerLocal userManager;

    /**
     * Method GET to show the sign in page
     * @param request http request
     * @param response http response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //we set a value true to tabSelect to display the tab signin in the form
        request.setAttribute("tabSelect",true);
        request.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(request, response);
    }

    /**
     * Method POST to sign in a user
     * @param request http request
     * @param response http response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the value username and password for the sign in
        String username = request.getParameter("usernameS");
        String password = request.getParameter("passwordS");

        //Try the connection
        if(userManager.signIn(username,password)) {

            //Create the session with the user info (without passowrd)
            HttpSession session = request.getSession();
            session.setAttribute("userSession",userManager.findUserByUserame(username));

            //Set a status as OK to said the request pass
            response.setStatus(HttpServletResponse.SC_OK);

            //redirect to the home page
            response.sendRedirect(request.getContextPath() + "/home");
        }else {

            //Set a error to say the connection fail
            request.setAttribute("error","Invalid Login or password");
            request.setAttribute("tabSelect",true);

            //Set the status forbidden
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            request.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(request, response);
        }



    }
}
