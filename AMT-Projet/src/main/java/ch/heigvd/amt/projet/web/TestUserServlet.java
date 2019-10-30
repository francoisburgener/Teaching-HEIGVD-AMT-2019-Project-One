package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = "/user" , name = "TestServlet")
public class TestServlet extends HttpServlet {

    @EJB
    private UsersManagerLocal userManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("users",userManager.findAllUsers());
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request,response);
    }
}
