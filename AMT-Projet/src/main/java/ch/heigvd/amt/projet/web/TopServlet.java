package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.TripManagerLocal;
import ch.heigvd.amt.projet.dao.UsersManagerLocal;
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

@WebServlet(urlPatterns = "/top", name = "TopServlet")
public class TopServlet extends HttpServlet {

    @EJB
    private TripManagerLocal tripManager;

    /**
     * Method GET to show the top ten visited country
     * @param request Http request
     * @param response http response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String tops = gson.toJson(tripManager.topTenVisiedCountry());


        request.setAttribute("tops", tops);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/top.jsp").forward(request,response);
    }
}
