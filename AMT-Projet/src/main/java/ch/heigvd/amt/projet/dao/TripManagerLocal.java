package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Country;
import ch.heigvd.amt.projet.model.Trip;
import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TripManagerLocal {
    List<Trip> findAllTripByUsername(String username,String countryName, int offset, int size);
    int createTrip(Trip trip);
    boolean deleteTrip(Trip trip);
    boolean updateTrip(Trip trip);
}
