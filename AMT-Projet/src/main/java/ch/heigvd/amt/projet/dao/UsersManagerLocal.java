package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {
    public List<User> findAllUsers();
}
