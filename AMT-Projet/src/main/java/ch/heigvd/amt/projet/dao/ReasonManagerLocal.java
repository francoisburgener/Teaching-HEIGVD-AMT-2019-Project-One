package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Reason;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ReasonManagerLocal {
    List<Reason> findAllReasons();
}
