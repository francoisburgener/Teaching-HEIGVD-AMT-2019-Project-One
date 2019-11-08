package ch.heigvd.amt.projet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void itShouldPossibleToBuildACountry(){
        User user = User.builder()
                .id(1)
                .username("Tiago")
                .email("tiago.povoa@heig-vd.ch")
                .fullname("Tiago povoa")
                .password("i like banana")
                .build();
        Country country = Country.builder()
                .id(1)
                .name("France")
                .build();
        assertEquals(1,user.getId());
        assertEquals("Tiago",user.getUsername());
        assertEquals("tiago.povoa@heig-vd.ch",user.getEmail());
        assertEquals("Tiago povoa",user.getFullname());
        assertEquals("i like banana",user.getPassword());
    }

}