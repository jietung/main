package seedu.exercise.ui;

/**
 * A class to format text in chart panels.
 */
public class ChartTextUtil {

    /**
     * Returns the formatted title of line chart and bar chart.
     */
    public static String lineAndBarChartTitleFormatter(String category, String startDate, String endDate) {
        return firstLetterUpperCaseFormatter(category + " (" + startDate + " to " + endDate + ")");
    }

    /**
     * Returns the formatted title of pie chart.
     */
    public static String pieChartTitleFormatter(String category, String startDate, String endDate) {
        if (category.equals("exercise")) {
            return firstLetterUpperCaseFormatter(category + " Frequency (" + startDate + " to " + endDate + ")");
        } else {
            return firstLetterUpperCaseFormatter(category + " (" + startDate + " to " + endDate + ")");
        }
    }

    /**
     * Returns the y-axis label of bar chart.
     */
    public static String barChartLabelFormatter(String category) {
        if (category.equals("exercise")) {
            return firstLetterUpperCaseFormatter("quantity");
        } else {
            return "kcal";
        }
    }

    /**
     * Returns the y-axis label of line chart.
     */
    public static String lineChartLabelFormatter(String category) {
        if (category.equals("exercise")) {
            return firstLetterUpperCaseFormatter("frequency");
        } else {
            return "kcal";
        }
    }

    /**
     * Returns the string with first letter changed to upper case.
     */
    public static String firstLetterUpperCaseFormatter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
