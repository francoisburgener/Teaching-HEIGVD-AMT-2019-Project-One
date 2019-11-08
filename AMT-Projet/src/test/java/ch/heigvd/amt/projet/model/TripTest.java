package ch.heigvd.amt.projet.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TripTest {

    @Test
    public void itShouldPossibleToBuildACountry(){
       Trip trip = Trip.builder()
               .idTrip(1)
               .idUser(1)
               .idCountry(1)
               .date("1992-02-02")
               .visited(false)
               .build();

        assertEquals(1,trip.getIdTrip());
        assertEquals(1,trip.getIdUser());
        assertEquals(1,trip.getIdCountry());
        assertEquals("1992-02-02",trip.getDate());
        assertFalse(trip.isVisited());
    }

}