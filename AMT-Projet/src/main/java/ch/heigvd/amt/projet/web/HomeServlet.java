package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.TripManagerLocal;
import ch.heigvd.amt.projet.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( urlPatterns = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {

    @EJB
    private TripManagerLocal tripManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(tripManager.findAllTripByUsername(user.getUsername()));

        request.setAttribute("user", user);
        request.setAttribute("trips",jsonString);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
