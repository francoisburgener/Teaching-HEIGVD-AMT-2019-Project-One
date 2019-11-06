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
    private UsersManagerLocal userManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("tabSelect",true);
        request.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameS");
        String password = request.getParameter("passwordS");

        HttpSession session = request.getSession();

        if(userManager.signIn(username,password)) {
            session.setAttribute("userSession",userManager.findUserByUserame(username));
            response.sendRedirect(request.getContextPath() + "/home");
        }else {
            request.setAttribute("error","Invalid Login or password");
            request.setAttribute("tabSelect",true);
            request.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(request, response);
        }



    }
}
