package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;

import java.util.List;

public interface TripManagerLocal {
    List<Trip> findAllTripByUsername(String username);

}
