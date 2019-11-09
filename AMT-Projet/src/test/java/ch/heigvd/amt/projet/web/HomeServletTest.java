package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.CountryManagerLocal;
import ch.heigvd.amt.projet.dao.TripManagerLocal;
import ch.heigvd.amt.projet.model.Country;
import ch.heigvd.amt.projet.model.Trip;
import ch.heigvd.amt.projet.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HomeServletTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    TripManagerLocal tripManager;

    @Mock
    CountryManagerLocal countryManager;

    @Mock
    HttpSession httpSession;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    User user;

    @Mock
    PrintWriter printWriter;


    HomeServlet servlet;


    @BeforeEach
    public void setup() throws IOException {
        servlet = new HomeServlet();
        servlet.tripManager = tripManager;
        servlet.countryManager = countryManager;
    }


    /*@Test Can't mock final class (Gson)
    public void doGetShouldfindAllTripByUserNameAndSearch() throws ServletException, IOException {

        Gson gson = new GsonBuilder().registerTypeAdapter(mock(Gson.class).getClass(), new Gson()).create();

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(request.getParameter("page")).thenReturn("1");
        when(request.getParameter("country-search")).thenReturn("France");
        when(tripManager.findAllTripByUsername(user.getUsername(),"France",0,8)).thenReturn(tripList);
        when(countryManager.findAllCountries()).thenReturn(countryList);
        when(gson.toJson(tripList)).thenReturn(anyString());
        when(gson.toJson(countryList)).thenReturn(anyString());

        when(request.getRequestDispatcher("/WEB-INF/pages/home.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request,response);

        verify(request,atLeastOnce()).setAttribute("coutryName","&country-search=France");
        verify(request,atLeastOnce()).setAttribute("user",user);
        verify(request,atLeastOnce()).setAttribute("countries",countryList);
        verify(request,atLeastOnce()).setAttribute("trips",tripList);
        verify(request,atLeastOnce()).setAttribute("page",1);
        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }*/

    @Test
    public void doPostShouldCreateTripStatusOK() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("POST");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(tripManager.createTrip(any())).thenReturn(1);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter, atLeastOnce()).write("1");
        verify(printWriter, atLeastOnce()).flush();
        verify(printWriter, atLeastOnce()).close();
    }

    @Test
    public void doPostShouldCreateTripStatusBadRequest() throws IOException, ServletException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("POST");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(tripManager.createTrip(any())).thenReturn(0);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void doPostShouldDeleteTripStatusOK() throws IOException, ServletException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("DELETE");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(request.getParameter("idTrip")).thenReturn("1");
        when(tripManager.deleteTrip(any())).thenReturn(true);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void doPostShouldDeleteTripStatusBadRequest() throws IOException, ServletException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("DELETE");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(request.getParameter("idTrip")).thenReturn("1");
        when(tripManager.deleteTrip(any())).thenReturn(false);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void doPostShouldUpdateTripStatusOK() throws IOException, ServletException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("UPDATE");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(request.getParameter("idTrip")).thenReturn("1");
        when(tripManager.updateTrip(any())).thenReturn(true);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void doPostShouldUpdateTripStatusBadRequest() throws IOException, ServletException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("action")).thenReturn("UPDATE");
        when(request.getParameter("idCountry")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("1999-09-09");
        when(request.getParameter("visited")).thenReturn("true");
        when(request.getParameter("idTrip")).thenReturn("1");
        when(tripManager.updateTrip(any())).thenReturn(false);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }



}