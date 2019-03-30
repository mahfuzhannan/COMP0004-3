package uk.ac.ucl.gui;

import uk.ac.ucl.Model;
import uk.ac.ucl.db.DatabaseConnector;
import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class View extends JFrame {
    private static final String TITLE = "Patient App";

    private Model model;

    private PatientsListPanel patientsListPanel;
    private PatientDetailsPanel patientDetailsPanel;
    private SearchPanel searchPanel;
    private IOPanel ioPanel;
    private StatisticsPanel statisticsPanel;


    public View(Model model) throws HeadlessException {
        super(TITLE);

        this.model = model;

        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout(10, 10));
    }

    public void init() {
        ioPanel = new IOPanel();
        ioPanel.setFileChooserActionListener(e -> {
            JFileChooser fileChooser = ioPanel.getFileChooser();
            int status = fileChooser.showDialog(this, "Load");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    populatePatients(model.readFile(fileChooser.getSelectedFile()));
                } catch (IOException e1) {
                    showErrorMessage("Could not load file.");
                }
            }
        });

        ioPanel.setSaveJsonActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Enter a filename");
            if (filename != null) {
                try {
                    String fileLocation = model.savePatientsJSON(filename + ".json");
                    showInfoMessage("File has been saved to: " + fileLocation);
                } catch (IOException e1) {
                    showErrorMessage("Failed to save patients to JSON.");
                }
            }
        });

        ioPanel.setLoadJsonActionListener(e -> {
            JFileChooser fileChooser = ioPanel.getFileChooser();
            int status = fileChooser.showDialog(this, "Load");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    populatePatients(model.loadPatientsJSON(fileChooser.getSelectedFile()));
                } catch (IOException e1) {
                    showErrorMessage("Could not load file.");
                }
            }
        });

        ioPanel.setSaveDatabaseActionListener(e -> {
            try {
                model.savePatientsDB();
                showInfoMessage("Saved to database!");
            } catch (SQLException | ClassNotFoundException err) {
                err.printStackTrace();
                showErrorMessage("Failed to save data");
            }
        });

        ioPanel.setLoadDatabaseActionListener(e -> {
            try {
                populatePatients(model.loadPatientsFromDB());
            } catch (SQLException | ClassNotFoundException e1) {
                e1.printStackTrace();
                showErrorMessage("Failed to load data from database" );
            }
        });

        this.add(ioPanel, BorderLayout.NORTH);

        patientsListPanel = new PatientsListPanel();
        patientsListPanel.addListSelectionListener(e -> {
            int[] selected = patientsListPanel.getSelectionModel().getSelectedIndices();
            if (selected.length > 0) {
                populatePatient(patientsListPanel.getListModel().get(selected[0]));
            }
        });

        patientDetailsPanel = new PatientDetailsPanel();

        searchPanel = new SearchPanel();
        searchPanel.setSearchActionListener(e -> {
            populatePatients(model.filterPatients(searchPanel.getSearchByNameText(), searchPanel.getSearchByAddressText()));
        });


        JPanel patientsPanel = new JPanel(new BorderLayout());
        patientsPanel.setBorder(new TitledBorder("Patients"));
        patientsPanel.add(searchPanel, BorderLayout.NORTH);
        patientsPanel.add(new JScrollPane(patientsListPanel), BorderLayout.WEST);
        patientsPanel.add(new JScrollPane(patientDetailsPanel), BorderLayout.CENTER);

        this.add(patientsPanel, BorderLayout.CENTER);

        statisticsPanel = new StatisticsPanel();
        this.add(statisticsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void populatePatients(List<Patient> patients) {
        patientsListPanel.clearSelection();
        patientsListPanel.setPatients(patients);
        statisticsPanel.populateAverageAge(model.getAverageAge());
        statisticsPanel.populateCommonBirthDate(model.getCommonBirthDate());
        statisticsPanel.populateCommonCity(model.getCommonCity());
    }

    private void populatePatient(Patient patient) {
        patientDetailsPanel.setPatient(patient);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "An error occured", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
