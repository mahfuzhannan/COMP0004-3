package uk.ac.ucl.daos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ucl.SetupDatabase;
import uk.ac.ucl.db.DatabaseConnector;
import uk.ac.ucl.entities.Patient;
import uk.ac.ucl.io.ReadCSV;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class PatientDaoTest {

    private static final String TEST_DB = "test.db";
    private PatientDao patientDao;

    @Before
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException, IOException {
        DatabaseConnector.setDatabasePath(TEST_DB);
        String schemaStr = Files.readString(Paths.get(SetupDatabase.class.getResource("/schema.sql").toURI()));
        Statement statement = DatabaseConnector.getInstance().getStatement();

        for(String query : schemaStr.split(";")) {
            statement.executeUpdate(query);
        }

        statement.close();

        patientDao = new PatientDao();
    }

    @After
    public void tearDown() throws IOException, SQLException, ClassNotFoundException {
        Files.delete(Paths.get(TEST_DB));
    }

    @Test
    public void testSavePatient() throws URISyntaxException, IOException, SQLException, ClassNotFoundException {
        List<Patient> patients = new ReadCSV().getPatients(new File(getClass().getResource("/patients1.csv").toURI()));
        patientDao.savePatients(patients);

        Patient patient = patients.get(0);

        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery("SELECT * FROM PATIENT");
        rs.next();
        assertThat(rs.getString("ID"), equalTo(patient.getId().toString()));

        assertThat(patientDao.getPatient(patient.getId()), equalTo(patient));
    }

    @Test
    public void testLoadPatients() throws URISyntaxException, IOException, SQLException, ClassNotFoundException {
        List<Patient> patients = new ReadCSV().getPatients(new File(getClass().getResource("/patients1.csv").toURI()));
        patientDao.savePatients(patients);
        assertThat(patientDao.getPatients(), equalTo(patients));
    }


    @Test
    public void testLoadPatients100() throws URISyntaxException, IOException, SQLException, ClassNotFoundException {
        List<Patient> patients = new ReadCSV().getPatients(new File(getClass().getResource("/patients100.csv").toURI()));
        patientDao.savePatients(patients);
        assertThat(patients.size(), equalTo(105));
        assertThat(patientDao.getPatients(), equalTo(patients));
    }

}