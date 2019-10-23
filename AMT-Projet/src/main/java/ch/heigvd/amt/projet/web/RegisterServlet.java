package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;
import ch.heigvd.amt.projet.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @EJB
    private UsersManagerLocal userManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("tabSelect", false);
        req.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassowrd = req.getParameter("confirm-password");

        if( !username.isEmpty() && !fullname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassowrd.isEmpty()){
          if(userManager.checkPassword(password,confirmPassowrd)){
            User user = new User(username, fullname, email, password);
            userManager.createUser(user);
            req.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(req, resp); // TODO Remove ?
          }else{
            //TODO Send error to the page
          }
        }else{
          //TODO Send error to the page
        }


    }
}
