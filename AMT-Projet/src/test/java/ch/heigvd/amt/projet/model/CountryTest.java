package ch.heigvd.amt.projet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    public void itShouldPossibleToBuildACountry(){
        Country country = Country.builder()
                .id(1)
                .name("France")
                .build();
        assertEquals("France",country.getName());
        assertEquals(1,country.getId());
    }

}