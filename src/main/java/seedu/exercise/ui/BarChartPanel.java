package seedu.exercise.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.exercise.logic.commands.statistic.Statistic;

public class BarChartPanel extends UiPart<Region> {
    private static final String FXML = "BarChartPanel.fxml";

    private Statistic statistic;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String, Double> barChart;

    public BarChartPanel(Statistic statistic) {
        super(FXML);
        this.statistic = statistic;
        display();
    }

    private void display() {
        barChart.setAnimated(false);
        barChart.layout();

        xAxis.setLabel(statistic.getCategory());
        yAxis.setLabel("quantity");

        XYChart.Series series = new XYChart.Series();

        ArrayList<String> names = statistic.getProperties();
        ArrayList<Double> values = statistic.getValues();

        int size = names.size();
        for (int i = 0; i < size; i++) {
            series.getData().add(new XYChart.Data<>(names.get(i), values.get(i)));
        }

        barChart.setLegendVisible(false);
        barChart.getData().add(series);
    }
}
