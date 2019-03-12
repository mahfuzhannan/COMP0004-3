package uk.ac.ucl.json;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import uk.ac.ucl.entities.Patient;
import uk.ac.ucl.io.ReadCSV;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class JSONFormatterTest {

    private JSONFormatter formatter;

    private ReadCSV readCSV;

    @Before
    public void setUp() throws Exception {
        formatter = new JSONFormatter();
        readCSV = new ReadCSV();
    }

    @Test
    public void formatPatient() throws URISyntaxException, IOException, JSONException {
        File csvFile = new File(getClass().getClassLoader().getResource("patients1.csv").toURI());
        List<Patient> patients = readCSV.getPatients(csvFile);

        String result = formatter.formatPatients(patients);
        System.out.println(result);
        assertThat(patients.size(), equalTo(1));
        String expectedJson = "[{" +
                "id:\"3e9fd20e-b81c-4698-a7c2-0559e10361fa\", gender:\"F\", birthDate:\"1979-08-24\", deathDate:\"null\"," +
                "name:{prefix:\"Mrs.\",first:\"Norah104\",last:\"Stamm704\",suffix:\"\"}," +
                "identification:{ssn:\"999-46-5746\",drivers:\"S99951588\",passport:\"X43863756X\"}," +
                "details:{maiden:\"Hagenes547\",marital: \"M\",race: \"white\",ethnicity: \"french\",birthplace: \"Hanover  Massachusetts  US\"}," +
                "address:{address: \"678 Trantow Overpass\",city: \"New Bedford\",state: \"Massachusetts\",zip: \"02740\"}" +
                "}]";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }
}