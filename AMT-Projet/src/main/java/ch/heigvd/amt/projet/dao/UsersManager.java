package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.User;

import javax.annotation.Resource;
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

@Stateless
public class UsersManager implements UsersManagerLocal{

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @Override
    public List<User> findAllUsers() {

        List<User> users = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();

            System.out.println("Schema : " + connection.getSchema());

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("idUser");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String password = rs.getString("password");

                users.add(new User(id,username,fullname,email,password));
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }
        return users;
    }
}
