package seedu.exercise.logic.commands.statistic;

import java.util.ArrayList;

public class Statistic {
    private String category;
    private String chart;
    private ArrayList<String> properties;
    private ArrayList<Double> values;

    public Statistic(String category, String chart, ArrayList<String> properties, ArrayList<Double> values) {
        this.category = category;
        this.chart = chart;
        this.properties = properties;
        this.values = values;
    }

    public String getCategory() {
        return category;
    }

    public String getChart() {
        return chart;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public ArrayList<Double> getValues() {
        return values;
    }
}
