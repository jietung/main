package seedu.exercise.logic.commands.statistic;

import static seedu.exercise.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

/**
 * Represents a Statistic with data needed to generate chart.
 */
public class Statistic {
    private String category;
    private String chart;
    private ArrayList<String> properties;
    private ArrayList<Double> values;

    /**
     * Every field must be present and not null.
     */
    public Statistic(String category, String chart, ArrayList<String> properties, ArrayList<Double> values) {
        requireAllNonNull(category, chart, properties, values);
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
