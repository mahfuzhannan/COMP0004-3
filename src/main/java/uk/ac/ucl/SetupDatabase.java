package uk.ac.ucl;

import uk.ac.ucl.db.DatabaseConnector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, URISyntaxException, IOException {
        String clearStr = Files.readString(Paths.get(SetupDatabase.class.getResource("/clear.sql").toURI()));
        String schemaStr = Files.readString(Paths.get(SetupDatabase.class.getResource("/schema.sql").toURI()));
        Statement statement = DatabaseConnector.getInstance().getStatement();

        for(String query : clearStr.split(";")) {
            statement.execute(query);
        }
        for(String query : schemaStr.split(";")) {
            statement.executeUpdate(query);
        }
    }
}
