package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.CountryManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/country", name = "TestCountryServlet")
public class TestCountryServlet extends HttpServlet {

    @EJB
    private CountryManagerLocal countryManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("countries",countryManager.findAllCountries());
        request.getRequestDispatcher("/WEB-INF/pages/country.jsp").forward(request,response);
    }
}
