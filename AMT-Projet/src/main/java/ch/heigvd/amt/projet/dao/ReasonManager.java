package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Reason;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReasonManager implements ReasonManagerLocal {

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @Override
    public List<Reason> findAllReasons() {
        List<Reason> reasons = new ArrayList<>();

        return null;
    }
}
