package ch.heigvd.amt.projet.dao;

import ch.heigvd.amt.projet.model.Reason;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ReasonManagerTest {

    @EJB
    ReasonManagerLocal reasonManager;

    @Test
    public void itShouldBePossibleToFindAllReasons() {
        List<Reason> reasonList = reasonManager.findAllReasons();
        assertNotNull(reasonList);
    }
}