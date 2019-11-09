package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.dao.exception.DuplicateKeyException;
import ch.heigvd.amt.projet.dao.exception.KeyNotFoundException;
import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {
    List<User> findAllUsers();
    User findUserByUserame(String username);
    boolean createUser(User user);
    User updateUserInfo(User user);
    boolean updateUserPassword(User user);
    boolean signIn(String username, String password);
}
