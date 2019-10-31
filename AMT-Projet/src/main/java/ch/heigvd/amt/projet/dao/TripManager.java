package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;

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
public class TripManager implements TripManagerLocal {

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @Override
    public List<Trip> findAllTripByUsername(String username) {
        List<Trip> trips = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT trip.Country_idCountry,trip.visited,trip.date, country.name\n" +
                    "FROM Trip trip\n" +
                    "LEFT JOIN User user ON trip.User_idUser = user.idUser\n" +
                    "LEFT JOIN Country country ON trip.Country_idCountry = country.idCountry\n" +
                    "WHERE user.username = " + "'" + username +"'");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int idCountry = rs.getInt("Country_idCountry");
                boolean visited = rs.getBoolean("visited");
                String dateTrip = rs.getDate("date").toString();
                String countyName = rs.getString("name");
                trips.add(Trip.builder().idCountry(idCountry).countryName(countyName).date(dateTrip).visited(visited).build());
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }

        return trips;
    }

    @Override
    public void deleteTrip(int idCountry, int idUser) {

    }
}
