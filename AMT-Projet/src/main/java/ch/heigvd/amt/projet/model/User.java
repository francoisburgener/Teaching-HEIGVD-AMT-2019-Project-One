package ch.heigvd.amt.projet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor

public class User {
  private String username;
  private String fullname;
  private String email;
  private String password;
}
