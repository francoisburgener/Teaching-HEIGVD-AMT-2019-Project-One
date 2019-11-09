package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SignOutServletTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession httpSession;

    SignOutServlet servlet;

    @BeforeEach
    public void setup(){
        servlet = new SignOutServlet();
    }

    @Test
    public void doGetShouldSignOut() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);

        servlet.doGet(request,response);

        verify(httpSession,atLeastOnce()).invalidate();
        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/signin");
    }

}