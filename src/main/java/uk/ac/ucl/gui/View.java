package uk.ac.ucl.gui;

import uk.ac.ucl.Model;
import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class View extends JFrame {
    private static final String TITLE = "Patient App";

    private Model model;

    private JButton openFileChooserButton;
    private JFileChooser fileChooser;

    private DefaultListModel<Patient> listModel;

    private PatientPanel patientPanel;

    public View(Model model) throws HeadlessException {
        super(TITLE);

        this.model = model;

        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout(10, 10));
    }

    public void init() {
        createFileChooser();
        this.add(getFileChooserPanel(), BorderLayout.NORTH);
        this.add(getPatientsPanel(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void createFileChooser() {
        fileChooser = new JFileChooser(new File("src/main/resources"));
        fileChooser.setDialogTitle("Select file to load");
        fileChooser.setMultiSelectionEnabled(false);
    }

    private JPanel getFileChooserPanel() {
        JPanel csvPanel = new JPanel(new BorderLayout());
        csvPanel.setBorder(new TitledBorder("File Load Panel"));
        openFileChooserButton = new JButton("Load File");
        openFileChooserButton.addActionListener(e -> {
            int status = fileChooser.showDialog(this, "Approve");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Patient> patientList = model.readFile(fileChooser.getSelectedFile());
                    listModel.addAll(patientList);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not load file.");
                }
            }
        });
        csvPanel.add(openFileChooserButton, BorderLayout.CENTER);
        return csvPanel;
    }

    private JPanel getPatientsPanel() {
        JPanel patientsPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        patientsPanel.setBorder(new TitledBorder("Patients"));

        listModel = new DefaultListModel<>();
        JList<Patient> patientJList = new JList<>(listModel);
        patientJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        patientJList.setLayoutOrientation(JList.VERTICAL);


        patientJList.getSelectionModel().addListSelectionListener(e -> {
            ListSelectionModel selectionModel = (ListSelectionModel)e.getSource();
            int[] selected = selectionModel.getSelectedIndices();
            try {
                populatePatient(listModel.get(selected[0]));
            } catch (InvocationTargetException | IllegalAccessException e1) {
                JOptionPane.showMessageDialog(this, "Failed to load patient details.");
            }
        });

        patientPanel = new PatientPanel();

        patientsPanel.add(new JScrollPane(patientJList));
        patientsPanel.add(new JScrollPane(patientPanel));
        return patientsPanel;
    }

    private void populatePatient(Patient patient) throws InvocationTargetException, IllegalAccessException {
        patientPanel.setPatient(patient);
    }
}
