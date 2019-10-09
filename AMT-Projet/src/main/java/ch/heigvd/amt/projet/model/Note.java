package ch.heigvd.amt.livecoding.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Note {

  private String text;
  private String title;
  private User author;
}
