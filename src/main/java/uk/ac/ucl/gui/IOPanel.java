package uk.ac.ucl.gui;

import uk.ac.ucl.Model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class IOPanel extends JPanel {

    private JFileChooser fileChooser;

    private JButton openFileChooserButton;
    private JButton saveJsonButton;
    private JButton loadJsonButton;
    private JButton saveDatabaseButton;
    private JButton loadDatabaseButton;

    public IOPanel() {
        this.setLayout(new GridLayout(1, 3));
        this.setBorder(new TitledBorder("File I/O Panel"));
        
        openFileChooserButton = new JButton("Load CSV");
        fileChooser = new JFileChooser(new File("src/main/resources"));
        fileChooser.setDialogTitle("Select file to load");
        fileChooser.setMultiSelectionEnabled(false);

        saveJsonButton = new JButton("Save JSON");
        loadJsonButton = new JButton("Load JSON");
        saveDatabaseButton = new JButton("Save to Database");
        loadDatabaseButton = new JButton("Load from Database");

        this.add(openFileChooserButton);
        this.add(saveJsonButton);
        this.add(loadJsonButton);
        this.add(saveDatabaseButton);
        this.add(loadDatabaseButton);
    }
    
    public void setFileChooserActionListener(ActionListener actionListener) {
        openFileChooserButton.addActionListener(actionListener);
    }

    public void setSaveJsonActionListener(ActionListener actionListener) {
        saveJsonButton.addActionListener(actionListener);
    }

    public void setLoadJsonActionListener(ActionListener actionListener) {
        loadJsonButton.addActionListener(actionListener);
    }

    public void setSaveDatabaseActionListener(ActionListener actionListener) {
        saveDatabaseButton.addActionListener(actionListener);
    }

    public void setLoadDatabaseActionListener(ActionListener actionListener) {
        loadDatabaseButton.addActionListener(actionListener);
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JButton getOpenFileChooserButton() {
        return openFileChooserButton;
    }

    public JButton getSaveJsonButton() {
        return saveJsonButton;
    }
}
