package ch.heigvd.amt.projet.business;

import javax.ejb.Local;

@Local
public interface IAuthenticationService {
    String hashPassword(String password);
    boolean checkPassord(String password,String hashedPassword);
}
