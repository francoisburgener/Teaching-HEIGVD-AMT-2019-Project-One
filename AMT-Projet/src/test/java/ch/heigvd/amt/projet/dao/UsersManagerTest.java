package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.User;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.validation.constraints.AssertTrue;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class UsersManagerTest {

    @EJB
    UsersManagerLocal usersManager;

    @Test
    public void findAllUsers() {
        List<User> userList = usersManager.findAllUsers();
        assertNotNull(userList);
    }

    @Test
    public void findUserByUserame() {
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void createUser() {
        User user = User.builder()
                .fullname("testo")
                .username("testi")
                .password("testa")
                .email("testi@testo.com")
                .build();

        assertTrue(usersManager.createUser(user));
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void updateUserInfo() {

        User user = User.builder()
                .fullname("toto")
                .username("toto")
                .password("toto")
                .email("toto@toto.com")
                .build();
        assertNotNull(usersManager.updateUserInfo(user));
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void signIn() {

        assertTrue(usersManager.signIn("toto", "toto"));
    }
}