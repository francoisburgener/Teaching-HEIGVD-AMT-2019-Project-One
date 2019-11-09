package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;
import ch.heigvd.amt.projet.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SignInServletTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    UsersManagerLocal usersManager;

    @Mock
    User user;

    @Mock
    HttpSession httpSession;

    @Mock
    RequestDispatcher requestDispatcher;

    SignInServlet servlet;

    @BeforeEach
    public void setup() throws IOException{
        servlet = new SignInServlet();
        servlet.userManager = usersManager;
    }

    @Test
    void doGetShouldDoSigninView() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/signin.jsp")).thenReturn(requestDispatcher);
        servlet.doGet(request, response);
        verify(requestDispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    void doPostShouldSignin() throws ServletException, IOException {
        when(request.getParameter("usernameS")).thenReturn("galiaker");
        when(request.getParameter("passwordS")).thenReturn("toto");
        when(usersManager.signIn("galiaker","toto")).thenReturn(true);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn(any());
        when(usersManager.findUserByUserame("galiaker")).thenReturn(user);

        servlet.doPost(request,response);
        verify(usersManager,atLeastOnce()).signIn("galiaker","toto");
        verify(usersManager,atLeastOnce()).findUserByUserame("galiaker");
        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/home");
    }

    @Test
    void doPostShouldRefuseSignIn() throws ServletException, IOException {
        when(request.getParameter("usernameS")).thenReturn("galiaker");
        when(request.getParameter("passwordS")).thenReturn("toto");
        when(usersManager.signIn("galiaker","toto")).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/pages/signin.jsp")).thenReturn(requestDispatcher);


        servlet.doPost(request,response);
        verify(usersManager,atLeastOnce()).signIn("galiaker","toto");
        verify(request,atLeastOnce()).setAttribute(eq("error"),eq("Invalid Login or password"));
        verify(request,atLeastOnce()).setAttribute(eq("tabSelect"),eq(true));
        verify(requestDispatcher, atLeastOnce()).forward(request, response);



    }
}