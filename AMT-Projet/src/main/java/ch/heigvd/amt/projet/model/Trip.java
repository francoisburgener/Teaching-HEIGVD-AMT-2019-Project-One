package ch.heigvd.amt.projet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Trip {
    private int idCountry;
    private boolean visited;
    private String date;
    private String countryName;
}
