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
import java.util.HashMap;

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

        HashMap<String,String> errors = new HashMap<>();
        if(username.trim().isEmpty()){
            errors.put("username","Username cannot be empty");
        }else if(!userManager.isUsernameFree(username)){
            errors.put("username","Username is already used");
        }

        if(fullname.trim().isEmpty()){
            errors.put("fullname","Fullname cannot be empty");

        }

        if(email.trim().isEmpty()){
            errors.put("email","Email cannot be empty");
        }

        if(password.trim().isEmpty() && confirmPassowrd.trim().isEmpty() && userManager.checkPassword(password,confirmPassowrd)){
            errors.put("password","Passwords cannot be empty or not match");
        }

        if(errors.isEmpty()){
            User user = User.builder().username(username).fullname(fullname).email(email).password(password).build();
            userManager.createUser(user);
            resp.sendRedirect("signin");

        }else{
            req.setAttribute("errors",errors);
            req.setAttribute("tabSelect", false);
            req.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(req, resp);
        }
    }
}
