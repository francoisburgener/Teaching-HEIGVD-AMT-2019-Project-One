package ch.heigvd.amt.projet.web;

import ch.heigvd.amt.projet.dao.UsersManagerLocal;
import ch.heigvd.amt.projet.dao.exception.KeyNotFoundException;
import ch.heigvd.amt.projet.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


//Class to manage the profile of a user
@WebServlet(urlPatterns = "/home/profile", name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @EJB
    UsersManagerLocal userManager;

    /**
     * Method GET to update the info of a user
     * @param request http request
     * @param response http response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //We get the session user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        //We get the action of the post
        String editAction = request.getParameter("editAction");

        switch (editAction) {
            case "editUserProfile":

                //Field you want to modify
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");
                HashMap<String, String> errors = new HashMap<>();

                //Check empty field
                if (fullname.trim().isEmpty()) {
                    errors.put("fullname", "Fullname cannot be empty");
                }

                if (email.trim().isEmpty()) {
                    errors.put("email", "Email cannot be empty");
                }

                if (errors.isEmpty()) {
                    User newUser = User.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .fullname(fullname)
                            .email(email)
                            .build();

                    //update the user
                    if(userManager.updateUserInfo(newUser) != null){
                        //Regenerate the session user
                        request.getSession().invalidate();
                        request.getSession().setAttribute("userSession",newUser);

                        response.sendRedirect(request.getContextPath() + "/home/profile");
                    }
                } else {
                    
                    //Set the error to the profile page
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                }

                break;
        }



    }

    /**
     * Method GET to show the update form for the user info
     * @param request http request
     * @param response http response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);

    }
}
