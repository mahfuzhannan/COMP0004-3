package uk.ac.ucl.gui;

import javax.swing.*;
import java.awt.*;

public class LoadCsvPanel extends JPanel {

    private JFileChooser jFileChooser;

    public LoadCsvPanel() {
        this.setLayout(new BorderLayout(10 ,10));
        jFileChooser = createCsvFileChooser();

        this.add(jFileChooser, BorderLayout.CENTER);
    }

    private JFileChooser createCsvFileChooser() {
        jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Select CSV file to upload");
        return jFileChooser;
    }
}
