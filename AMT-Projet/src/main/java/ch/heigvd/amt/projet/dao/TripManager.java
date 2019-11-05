package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TripManager implements TripManagerLocal {

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @Override
    public List<Trip> findAllTripByUsername(String username) {
        List<Trip> trips = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM Trip trip\n" +
                    "LEFT JOIN User user ON trip.User_idUser = user.idUser\n" +
                    "WHERE user.username = " + "'" + username +"'");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int idTrip = rs.getInt("idTrip");
                int idUser = rs.getInt("User_idUser");
                int idCountry = rs.getInt("Country_idCountry");
                boolean visited = rs.getBoolean("visited");
                String dateTrip = rs.getDate("date").toString();
                trips.add(Trip.builder().idTrip(idTrip).idUser(idUser).idCountry(idCountry).date(dateTrip).visited(visited).build());
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }

        return trips;
    }

    @Override
    public int createTrip(Trip trip) {
        int id = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Trip (User_idUser, Country_idCountry, visited, date) VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,trip.getIdUser());
            pstmt.setInt(2,trip.getIdCountry());
            pstmt.setBoolean(3,trip.isVisited());
            pstmt.setString(4,trip.getDate());
            pstmt.executeUpdate();
            connection.close();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }

        return id;
    }

    @Override
    public boolean deleteTrip(Trip trip) {

        boolean check = false;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Trip WHERE Trip.idTrip = ? AND Trip.User_idUser = ? AND Trip.Country_idCountry = ?");
            pstmt.setInt(1,trip.getIdTrip());
            pstmt.setInt(2,trip.getIdUser());
            pstmt.setInt(3,trip.getIdCountry());
            pstmt.executeUpdate();
            connection.close();

            check = true;

        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return check;
        }

        return check;
    }

    @Override
    public boolean updateTrip(Trip trip) {

        boolean check = false;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Trip SET visited = ?, date = ? WHERE Trip.idTrip = ? AND Trip.User_idUser = ? AND Trip.Country_idCountry = ?;");

            pstmt.setBoolean(1,trip.isVisited());
            pstmt.setString(2,trip.getDate());
            pstmt.setInt(3,trip.getIdTrip());
            pstmt.setInt(4,trip.getIdUser());
            pstmt.setInt(5,trip.getIdCountry());
            pstmt.executeUpdate();
            connection.close();

            check = true;

        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return check;
        }

        return check;
    }


}
