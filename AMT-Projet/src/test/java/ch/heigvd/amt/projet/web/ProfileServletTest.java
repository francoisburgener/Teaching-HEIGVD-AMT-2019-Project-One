package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;
import ch.heigvd.amt.projet.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileServletTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    UsersManagerLocal usersManager;

    @Mock
    User user;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    HashMap<String,String> errors;

    @Mock
    HttpSession httpSession;

    ProfileServlet servlet;

    @BeforeEach
    public void setup() throws IOException {
        servlet = new ProfileServlet();
        servlet.userManager = usersManager;
    }

    @Test
    public void doPostShouldEditProfilSuccess() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(request.getParameter("editAction")).thenReturn("editUserProfile");
        when(request.getParameter("fullname")).thenReturn("François Burgener");
        when(request.getParameter("email")).thenReturn("francois.burgener@hotmail.fr");
        when(usersManager.updateUserInfo(any(User.class))).thenReturn(user);

        servlet.doPost(request,response);

        verify(httpSession,atLeastOnce()).invalidate();
        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/home/profile");
    }


    @Test
    public void doPostShouldEditProfilEmptyField() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userSession")).thenReturn(user);
        when(request.getParameter("editAction")).thenReturn("editUserProfile");
        when(request.getParameter("fullname")).thenReturn("");
        when(request.getParameter("email")).thenReturn("");
        when(request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")).thenReturn(requestDispatcher);

        servlet.doPost(request,response);

        verify(requestDispatcher,atLeastOnce()).forward(request,response);
    }
}