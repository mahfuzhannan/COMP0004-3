package uk.ac.ucl.io;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ucl.entities.Patient;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class JsonFileHandlerTest {

    private JsonFileHandler jsonFileHandler;
    private ReadCSV readCSV;

    @Before
    public void setUp() {
        jsonFileHandler = new JsonFileHandler();
        readCSV = new ReadCSV();
    }

    @Test
    public void writeThenRead() throws URISyntaxException, IOException, InterruptedException {
        List<Patient> patients = readCSV.getPatients(new File(getClass().getClassLoader().getResource("patients100.csv").toURI()));

//        Path tempFilePath = Files.createTempFile("patients_test", ".json");
        String path = jsonFileHandler.write(patients, "patients_test.json");

        List<Patient> result = jsonFileHandler.read(new File(path));

        assertThat(result, equalTo(patients));
    }

}