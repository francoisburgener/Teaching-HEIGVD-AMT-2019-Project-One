package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {
    List<User> findAllUsers();
    User findUserByUserame(String username);
    boolean createUser(User user);
    boolean signIn(String username, String password);
    boolean isUsernameFree(String username);

    boolean checkPassword(String password, String confirmPassowrd);
}
