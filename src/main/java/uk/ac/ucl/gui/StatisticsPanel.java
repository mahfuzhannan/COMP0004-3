package uk.ac.ucl.gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private JTextField averageAgeField;
    private JTextField commonBirthDate;
    private JTextField commonCity;

    public StatisticsPanel() {
        this.setLayout(new GridLayout(1, 8));
        averageAgeField = new JTextField();
        commonBirthDate = new JTextField();
        commonCity = new JTextField();

        this.add(new JLabel("Ave Age"));
        this.add(averageAgeField);
        this.add(new JLabel("Com. Birth Date"));
        this.add(commonBirthDate);
        this.add(new JLabel("Com. City"));
        this.add(commonCity);
    }

    public void populateAverageAge(Integer ave) {
        averageAgeField.setText(ave.toString());
    }

    public void populateCommonBirthDate(String ave) {
        commonBirthDate.setText(ave);
    }

    public void populateCommonCity(String ave) {
        commonCity.setText(ave);
    }
}
