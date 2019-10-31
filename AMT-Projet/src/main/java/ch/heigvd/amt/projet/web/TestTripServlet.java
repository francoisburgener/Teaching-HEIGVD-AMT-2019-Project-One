package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.TripManagerLocal;
import ch.heigvd.amt.projet.dao.UsersManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/trip", name = "TestTripServlet")
public class TestTripServlet extends HttpServlet {

    @EJB
    private TripManagerLocal tripManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = "galiaker";
        request.setAttribute("username",username);
        request.setAttribute("trips",tripManager.findAllTripByUsername(username));
        request.getRequestDispatcher("/WEB-INF/pages/trip.jsp").forward(request,response);
    }
}
