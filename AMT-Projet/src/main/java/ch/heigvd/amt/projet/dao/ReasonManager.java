package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Reason;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ReasonManager implements ReasonManagerLocal {

    @Resource(lookup = "jdbc/amt-db")
    private DataSource dataSource;

    @Override
    public List<Reason> findAllReasons() {
        List<Reason> reasons = new ArrayList<>(); //todo

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Reason;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("idReason");
                String name = rs.getString("name");
                reasons.add(Reason.builder().id(id).name(name).build());
            }
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE,null,ex);
        }
        return reasons;
    }
}
