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
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("userSession");
        int idUser = user.getId(); // pour tous
        String idCountry = request.getParameter("idCountry"); // pour tous
        String date = request.getParameter("date"); // pour tous
        Boolean visited = Boolean.parseBoolean(request.getParameter("visited")); // pour tous
        Trip trip = null;
        String idTrip;
        switch (action){
            case "POST":
                trip = Trip.builder().idCountry(Integer.parseInt(idCountry)).idUser(idUser).date(date).visited(visited).build();
                int id = tripManager.createTrip(trip);
                if(id != 0){

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(""+ id);
                    response.getWriter().flush();
                    response.getWriter().close();
                }else{
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "DELETE":
                idTrip = request.getParameter("idTrip");
                trip = Trip.builder().idTrip(Integer.parseInt(idTrip)).idCountry(Integer.parseInt(idCountry)).idUser(idUser).date(date).visited(visited).build();
                if(tripManager.deleteTrip(trip)){
                    response.setStatus(HttpServletResponse.SC_OK);
                }else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "UPDATE":
                idTrip = request.getParameter("idTrip");
                trip = Trip.builder().idTrip(Integer.parseInt(idTrip)).idCountry(Integer.parseInt(idCountry)).idUser(idUser).date(date).visited(visited).build();
                if(tripManager.updateTrip(trip)){
                    response.setStatus(HttpServletResponse.SC_OK);
                }else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
        }
    }
}