package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TripManagerLocal {
    List<Trip> findAllTripByUsername(String username);
    int createTrip(Trip trip);
    boolean deleteTrip(Trip trip);
    boolean updateTrip(Trip trip);
}
