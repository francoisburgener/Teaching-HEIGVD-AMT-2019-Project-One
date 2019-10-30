package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Country;

import java.util.List;

public interface CountryManagerLocal {
    List<Country> findAllCountries();
}
