package ch.heigvd.amt.projet.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class City {

  private int id;
  private String name;
}
