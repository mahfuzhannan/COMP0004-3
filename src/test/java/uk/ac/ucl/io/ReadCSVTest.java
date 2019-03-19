package uk.ac.ucl.io;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ucl.entities.Patient;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ReadCSVTest {

    private ReadCSV readCsv;

    @Before
    public void setUp() {
        readCsv = new ReadCSV();
    }

    @Test
    public void testGetPatients_100() throws URISyntaxException, IOException {
        File csvFile = new File(getClass().getClassLoader().getResource("data/patients100.csv").toURI());
        List<Patient> patients = readCsv.getPatients(csvFile);
        assertThat(patients.size(), equalTo(105));
    }

    @Test
    public void testGetPatients_1000() throws URISyntaxException, IOException {
        File csvFile = new File(getClass().getClassLoader().getResource("data/patients1000.csv").toURI());
        List<Patient> patients = readCsv.getPatients(csvFile);
        assertThat(patients.size(), equalTo(1116));
    }

    @Test
    public void testGetPatients_10000() throws URISyntaxException, IOException {
        File csvFile = new File(getClass().getClassLoader().getResource("data/patients10000.csv").toURI());
        List<Patient> patients = readCsv.getPatients(csvFile);
        assertThat(patients.size(), equalTo(11151));
    }

    @Test
    public void testGetPatients_100000() throws URISyntaxException, IOException {
        File csvFile = new File(getClass().getClassLoader().getResource("data/patients100000.csv").toURI());
        List<Patient> patients = readCsv.getPatients(csvFile);
        assertThat(patients.size(), equalTo(111165));
    }
}