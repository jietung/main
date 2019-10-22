package seedu.exercise.logic.commands.statistic;

import java.util.ArrayList;

public class Statistic {
    private String category;
    private String chart;
    private ArrayList<String> names;
    private ArrayList<Double> values;

    public Statistic(String category, String chart, ArrayList<String> names, ArrayList<Double> values) {
        this.category = category;
        this.chart = chart;
        this.names = names;
        this.values = values;
    }

    public String getCategory() {
        return category;
    }

    public String getChart() {
        return chart;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Double> getValues() {
        return values;
    }
}
