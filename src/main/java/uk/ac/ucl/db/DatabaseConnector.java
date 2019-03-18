package uk.ac.ucl.db;

import uk.ac.ucl.SetupDatabase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static DatabaseConnector INSTANCE = null;
    private static String DATABASE_PATH = "patients.db";

    private Connection connection;

    private DatabaseConnector() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
        connection.setAutoCommit(true);
    }

    public static DatabaseConnector getInstance() throws SQLException, ClassNotFoundException {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseConnector();
        }
        return INSTANCE;
    }

    public Statement getStatement() throws SQLException {
        Statement statement = INSTANCE.connection.createStatement();
        statement.closeOnCompletion();
        return statement;
    }

    public static void setDatabasePath(String path) throws SQLException, ClassNotFoundException {
        DATABASE_PATH = path;
        INSTANCE = new DatabaseConnector();
    }
}
