package seedu.exercise.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.exercise.logic.commands.statistic.Statistic;

/**
 * An UI for pie chart.
 */
public class PieChartPanel extends UiPart<Region> {
    private static final String FXML = "PieChartPanel.fxml";

    private Statistic statistic;

    @FXML
    private PieChart pieChart;

    public PieChartPanel(Statistic statistic) {
        super(FXML);
        this.statistic = statistic;
        display();
    }

    private void display() {
        ArrayList<String> names = statistic.getProperties();
        ArrayList<Double> values = statistic.getValues();

        int size = names.size();
        for (int i = 0; i < size; i++) {
            PieChart.Data slice = new PieChart.Data(names.get(i), values.get(i));
            pieChart.getData().add(slice);
        }

        pieChart.setTitle(statistic.getCategory());
    }
}
