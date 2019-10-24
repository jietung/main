package seedu.exercise.testutil;

import java.util.ArrayList;

import seedu.exercise.logic.commands.statistic.Statistic;
import seedu.exercise.model.property.Date;

/**
 * A utility class to help with building Statistic objects.
 */
public class StatisticBuilder {

    private static final String DEFAULT_CATEGORY = "calories";
    private static final String DEFAULT_CHART = "linechart";
    private static final String DEFAULT_START_DATE = "17/10/2019";
    private static final String DEFAULT_END_DATE = "23/10/2019";

    private String category;
    private String chart;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> properties;
    private ArrayList<Double> values;

    public StatisticBuilder() {
        this.category = DEFAULT_CATEGORY;
        this.chart = DEFAULT_CHART;
        this.startDate = new Date(DEFAULT_START_DATE);
        this.endDate = new Date(DEFAULT_END_DATE);
        this.properties = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public Statistic build() {
        return new Statistic(category, chart, startDate, endDate, properties, values);
    }
}
