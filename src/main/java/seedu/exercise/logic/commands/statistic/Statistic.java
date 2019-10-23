package seedu.exercise.logic.commands.statistic;

import static seedu.exercise.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.exercise.model.property.Date;

/**
 * Represents a Statistic with data needed to generate chart.
 */
public class Statistic {
    private String category;
    private String chart;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> properties;
    private ArrayList<Double> values;

    /**
     * Every field must be present and not null.
     */
    public Statistic(String category, String chart, Date startDate, Date endDate,
                     ArrayList<String> properties, ArrayList<Double> values) {
        requireAllNonNull(category, chart, startDate, endDate, properties, values);
        this.category = category;
        this.chart = chart;
        this.startDate = startDate;
        this.endDate = endDate;
        this.properties = properties;
        this.values = values;
    }

    public String getCategory() {
        return category;
    }

    public String getChart() {
        return chart;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public ArrayList<Double> getValues() {
        return values;
    }
}
