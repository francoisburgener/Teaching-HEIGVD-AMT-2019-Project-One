package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {
    public List<User> findAllUsers();
    public void createUser(User user);
    public boolean signIn(String username, String password);

    public boolean checkPassword(String password, String confirmPassowrd);
}
