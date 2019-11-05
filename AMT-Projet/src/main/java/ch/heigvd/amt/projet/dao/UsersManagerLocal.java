package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.dao.exception.DuplicateKeyException;
import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {
    List<User> findAllUsers();
    User findUserByUserame(String username);
    void createUser(User user) throws DuplicateKeyException;
    boolean updateUser(User user);
    boolean signIn(String username, String password);
}
