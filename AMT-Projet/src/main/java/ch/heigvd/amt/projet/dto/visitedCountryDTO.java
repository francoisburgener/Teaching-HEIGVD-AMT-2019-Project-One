package ch.heigvd.amt.projet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class visitedCountryDTO {
    String countryName;
    int numberVisited;
}
