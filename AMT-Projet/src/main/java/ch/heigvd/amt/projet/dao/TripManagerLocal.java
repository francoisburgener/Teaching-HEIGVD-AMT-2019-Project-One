package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TripManagerLocal {
    List<Trip> findAllTripByUsername(String username);
    void deleteTrip(int idCountry, int idUser);
}
