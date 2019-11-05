package ch.heigvd.amt.projet.business;

import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

@Stateless
public class AuthenticateService implements IAuthenticationService {
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean checkPassord(String password, String hashedPassword) {
        try {
            boolean correct = BCrypt.checkpw(password,hashedPassword);
            return correct;
        }catch (Exception e){
            return false;
        }
    }
}
