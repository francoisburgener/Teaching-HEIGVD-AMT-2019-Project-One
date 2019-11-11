package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Trip;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class TripManagerTest {

    @EJB
    TripManagerLocal tripManager;

    final static int SIZE = 3;

    @Test
    public void itShouldBePossibleTofindAllTripByUsername() {
        List<Trip> trips = tripManager.findAllTripByUsername("toto", "", 0, SIZE);

        assertNotNull(trips);
    }

    @Test
    public void itShouldBePossibleToPaginateTheFind() {
        List<Trip> trips = tripManager.findAllTripByUsername("toto", "", 0, SIZE);

        assertEquals(trips.size(), SIZE);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateTrip() {
        Trip trip = Trip.builder()
                .idCountry(1)
                .idUser(10)
                .idReason(1)
                .visited(false)
                .build();

        assertNotEquals(tripManager.createTrip(trip), 0);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void deleteTrip() {
        Trip trip = Trip.builder()
                .idCountry(1)
                .idUser(10)
                .idReason(1)
                .visited(false)
                .build();

        assertTrue(tripManager.deleteTrip(trip));
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void updateTrip() {
        Trip trip = Trip.builder()
                .idCountry(1)
                .idUser(10)
                .idReason(1)
                .visited(true)
                .date("1992-02-02")
                .build();

        assertTrue(tripManager.updateTrip(trip));
    }

}