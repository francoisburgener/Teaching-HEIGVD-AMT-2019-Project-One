package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.business.IAuthenticationService;
import ch.heigvd.amt.projet.dao.exception.DuplicateKeyException;
import ch.heigvd.amt.projet.dao.exception.KeyNotFoundException;
import ch.heigvd.amt.projet.model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//DAO to manage the user
@Stateless
public class UsersManager implements UsersManagerLocal{

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @EJB
    IAuthenticationService authenticationService;

    /**
     * Method to get all users of our database
     * @return a list of user
     */
    @Override
    public List<User> findAllUsers() {

        List<User> users = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();

            System.out.println("Schema : " + connection.getSchema());

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("idUser");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String password = rs.getString("password");

                users.add(User.builder().id(id).username(username).fullname(fullname).email(email).password(password).build());
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
        return users;
    }


    /**
     * Method to find user in database by their username
     * @param username username to find
     * @return retour the user find in the database
     */
    @Override
    public User findUserByUserame(String username) {
        User user = null;

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User where username=?;");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            boolean record = rs.next();

            int id = rs.getInt("idUser");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            user = User.builder().id(id).username(username).fullname(fullname).email(email).build();
            connection.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    /**
     * Method to create a user in the database
     * @param user the user we want to create
     * @return true if success, false otherwise
     */
    @Override
    public boolean createUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO User(username,fullname,email,password) VALUES (?, ?, ?, ?);");
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getFullname());
            pstmt.setString(3,user.getEmail());

            String hashedPassWord = authenticationService.hashPassword(user.getPassword());
            pstmt.setString(4,hashedPassWord);
            pstmt.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * Method to update user info (fullname and email) in the database
     * @param user info user we want to update
     * @return the new user updated
     */
    @Override
    public User updateUserInfo(User user){

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE User SET fullname = ?, email = ? WHERE User.idUser = ?;");

            pstmt.setString(1,user.getFullname());
            pstmt.setString(2,user.getEmail());
            pstmt.setInt(3,user.getId());

            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }

        return user;
    }

    /**
     * Methode to update the password of a user
     * @param user info user we want to update
     * @return
     */
    @Override
    public boolean updateUserPassword(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE User SET password = ? WHERE User.idUser = ?;");

            String hashedPassword = authenticationService.hashPassword(user.getPassword());
            pstmt.setString(1,hashedPassword);
            pstmt.setInt(2,user.getId());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * Method to sign in a user (check if hashed password match with the plain text password)
     * @param username username of the user
     * @param password plain text password of the user
     * @return true if success and false otherwise
     */
    @Override
    public boolean signIn(String username, String password) {

        Boolean check = false;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT password FROM User WHERE username = '" + username + "'");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                String hashedPassword = rs.getString("password");
                check = authenticationService.checkPassord(password,hashedPassword);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

}
