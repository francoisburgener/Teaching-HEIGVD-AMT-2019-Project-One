package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/signin", name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("tabSelect",true);
        request.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO verify all field are not empty
        String username = request.getParameter("usernameS");
        String password = request.getParameter("passwordS");

        //TODO Check if password matches with the username

        request.getRequestDispatcher("/WEB-INF/pages/bonjour.jsp").forward(request, response);
    }
}
