package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Country;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CountryManagerLocal {
    List<Country> findAllCountries();
}
