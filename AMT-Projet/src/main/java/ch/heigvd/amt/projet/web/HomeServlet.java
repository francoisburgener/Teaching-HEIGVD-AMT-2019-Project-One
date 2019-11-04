package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.CountryManagerLocal;
import ch.heigvd.amt.projet.dao.TripManagerLocal;
import ch.heigvd.amt.projet.model.Trip;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet( urlPatterns = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {

    @EJB
    private TripManagerLocal tripManager;

    @EJB
    private CountryManagerLocal countryManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String trips = gson.toJson(tripManager.findAllTripByUsername(user.getUsername()));
        String countries = gson.toJson(countryManager.findAllCountries());

        request.setAttribute("user", user);
        request.setAttribute("countries",countries);
        request.setAttribute("trips",trips);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*User user = (User) request.getSession().getAttribute("userSession");
        int idCountry = 79 ; //TODO get parameter
        int idUser = user.getId();
        String date = "2020-12-22"; //TODO get parameter
        Boolean visited = false; //TODO get parameter
        */
        User user = (User) request.getSession().getAttribute("userSession");
        String idCountry = request.getParameter("idCountry");
        int idUser = user.getId();
        String date = request.getParameter("date");
        Boolean visited = Boolean.parseBoolean(request.getParameter("visited"));

        Trip trip = Trip.builder().idCountry(Integer.parseInt(idCountry)).idUser(idUser).date(date).visited(visited).build();
        if(tripManager.createTrip(trip)){
            response.setStatus(200);
        }else{
            response.setStatus(400);
        }
    }
}
