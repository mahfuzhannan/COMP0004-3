package uk.ac.ucl.io;

import uk.ac.ucl.entities.*;
import uk.ac.ucl.enums.Gender;
import uk.ac.ucl.json.JSONFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonFileHandler {

    private JSONFormatter jsonFormatter;

    public JsonFileHandler() {
        jsonFormatter = new JSONFormatter();
    }

    public List<Patient> read(File file) throws IOException {
        return jsonFormatter.deserializePatients(Files.lines(Paths.get(file.toURI())).collect(Collectors.joining()));
    }

    public String write(List<Patient> patients, String filename) throws IOException {
        Path filePath = Paths.get(filename);
        String json = jsonFormatter.serializePatients(patients);
        Files.writeString(filePath, json);
        return filePath.toAbsolutePath().toString();
    }

}