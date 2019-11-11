package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.dto.visitedCountryDTO;
import ch.heigvd.amt.projet.model.Country;
import ch.heigvd.amt.projet.model.Trip;
import ch.heigvd.amt.projet.model.User;

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

    /**
     * Method to get all trips of our database by the username
     * @param username username of the user
     * @param countryName string for searching by country
     * @param offset offset for the pagination
     * @param size size of our pagination
     * @return list of trip
     */
    @Override
    public List<Trip> findAllTripByUsername(String username, String countryName , int offset, int size) {
        List<Trip> trips = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM Trip as trip\n" +
                            "LEFT JOIN User as user ON trip.User_idUser = user.idUser\n" +
                            "LEFT JOIN Country as country ON trip.Country_idCountry = country.idCountry\n" +
                            "WHERE user.username = ? AND  country.Name LIKE ? \n" +
                            "ORDER BY trip.idTrip DESC\n" +
                            "LIMIT " + offset + ", " + size + ";"
            );
            pstmt.setString(1,username);
            pstmt.setString(2,countryName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int idTrip = rs.getInt("idTrip");
                int idUser = rs.getInt("User_idUser");
                int idCountry = rs.getInt("Country_idCountry");
                int idReason = rs.getInt("Reason_idReason");
                boolean visited = rs.getBoolean("visited");
                String dateTrip = rs.getDate("date").toString();
                trips.add(Trip.builder().idTrip(idTrip).idUser(idUser).idCountry(idCountry).idReason(idReason).date(dateTrip).visited(visited).build());
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }

        return trips;
    }

    /**
     * Methode to create a trip
     * @param trip trip we want to create
     * @return id of the last trip created
     */
    @Override
    public int createTrip(Trip trip) {
        int id = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Trip (User_idUser, Country_idCountry,Reason_idReason, visited, date) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,trip.getIdUser());
            pstmt.setInt(2,trip.getIdCountry());
            pstmt.setInt(3,trip.getIdReason());
            pstmt.setBoolean(4,trip.isVisited());
            pstmt.setString(5,trip.getDate());
            pstmt.executeUpdate();
            connection.close();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
            return 0;
        }

        return id;
    }

    /**
     * Methode to delete trip
     * @param trip trip we want to delete
     * @return true if success and false otherwise
     */
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

    /**
     * Method to update a trip (visited and date)
     * @param trip trip info we want to update
     * @return true if success and false otherwise
     */
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

    /**
     * Method to get the top 10 visited country
     * @return List of top ten visited country
     */
    @Override
    public List<visitedCountryDTO> topTenVisiedCountry() {
        List<visitedCountryDTO> visitedCountryDTOS = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT country.Name, count(*)\n" +
                    "FROM Trip as trip\n" +
                    "LEFT JOIN Country as country ON trip.Country_idCountry = country.idCountry\n" +
                    "WHERE trip.visited = 1\n" +
                    "GROUP BY trip.Country_idCountry\n" +
                    "ORDER BY count(*) DESC\n" +
                    "LIMIT 10;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String countryName = rs.getString("Name");
                int count = rs.getInt("count(*)");

                visitedCountryDTOS.add(visitedCountryDTO.builder().countryName(countryName).numberVisited(count).build());
            }

            connection.close();


        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }
        return visitedCountryDTOS;

    }
}
