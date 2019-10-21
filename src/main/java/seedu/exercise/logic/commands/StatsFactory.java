package seedu.exercise.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.property.Date;
import seedu.exercise.model.property.Name;
import seedu.exercise.model.property.Unit;
import seedu.exercise.model.resource.Exercise;

public class StatsFactory {

    private ObservableList<Exercise> exercises;
    private String chart;
    private String category;
    private Date startDate;
    private Date endDate;

    public StatsFactory(ReadOnlyResourceBook<Exercise> exercises, String chart, String category,
                        Date startDate, Date endDate) {
        this.exercises = exercises.getResourceList();
        this.chart = chart;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Chart generateChart() {
        HashMap<Name, Double> data;
        if (chart.equals("linechart")) {
            ArrayList<Date> dates = Date.getListOfDates(startDate, endDate);
            ArrayList<Double> values;
            if (category.equals("exercies")) {
                values = exerciseQuantityByDate(getFilteredExercise(), dates);
            } else {
                values = caloriesByDate(getFilteredExercise(), dates);
            }
            return generateLineChart(dates, values);
        } else {
            if (category.equals("exercise")) {
                data = getTotalExerciseQuantity();
            } else { //calories
                data = getTotalCaloriesData();
            }

            ArrayList<Name> names = hashMapNameToList(data);
            ArrayList<Double> values = hashMapDoubleToList(data, names);

            if (chart.equals("piechart")) {
                return generatePieChart(names, values);
            } else { //barchart
                return generateBarChart(names, values);
            }
        }
    }

    private BarChart generateBarChart(ArrayList<Name> names, ArrayList<Double> values) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(category);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("quantity");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        int size = names.size();
        for (int i = 0; i < size; i++) {
            series.getData().add(new XYChart.Data<>(names.get(i).toString(), values.get(i)));
        }

        barChart.getData().add(series);

        return barChart;
    }

    private LineChart generateLineChart(ArrayList<Date> dates, ArrayList<Double> values) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(category);

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();

        int size = dates.size();
        for (int i = 0; i < size; i++) {
            series.getData().add(new XYChart.Data<>(dates.get(i).toString(), values.get(i)));
        }

        lineChart.getData().add(series);

        return lineChart;
    }

    private PieChart generatePieChart(ArrayList<Name> names, ArrayList<Double> values) {
        PieChart pieChart = new PieChart();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            PieChart.Data slice = new PieChart.Data(names.get(i).toString(), values.get(i));
            pieChart.getData().add(slice);
        }
        return pieChart;
    }

    private HashMap<Name, Double> getTotalExerciseQuantity() {
        ArrayList<Exercise> filteredExercise = getFilteredExercise();
        HashMap<Name, Double> data = new HashMap<>();

        for (Exercise e : filteredExercise) {
            Name name = e.getName();
            Unit unit = e.getUnit();
            Name nameWithUnit = new Name(name.toString() + "(" + unit.toString() + ")");
            double quantity = Double.parseDouble(e.getQuantity().toString());

            if (data.containsKey(nameWithUnit)) {
                double temp = data.get(nameWithUnit);
                quantity += temp;
            }

            data.put(nameWithUnit, quantity);
        }

        return data;
    }

    private HashMap<Name, Double> getTotalCaloriesData() {
        ArrayList<Exercise> filteredExercise = getFilteredExercise();
        HashMap<Name, Double> data = new HashMap<>();

        for (Exercise e : filteredExercise) {
            Name name = e.getName();
            double calories = Double.parseDouble(e.getCalories().toString());

            if (data.containsKey(name)) {
                double temp = data.get(name);
                calories += temp;
            }

            data.put(name, calories);
        }

        return data;
    }

    private ArrayList<Exercise> getFilteredExercise() {
        ArrayList<Exercise> filteredExercise = new ArrayList<>();
        for (Exercise e : exercises) {
            Date date = e.getDate();
            if (Date.isBetweenStartAndEndDate(date, startDate, endDate)) {
                filteredExercise.add(e);
            }
        }

        return filteredExercise;
    }

    private ArrayList<Name> hashMapNameToList(HashMap<Name, Double> data) {
        return new ArrayList<>(data.keySet());
    }

    private ArrayList<Double> hashMapDoubleToList(HashMap<Name, Double> data, ArrayList<Name> names) {
        ArrayList<Double> values = new ArrayList<>();

        for (Name n : names) {
            values.add(data.get(n));
        }

        return values;
    }

    private ArrayList<Double> exerciseQuantityByDate(ArrayList<Exercise> exercises, ArrayList<Date> dates) {
        ArrayList<Double> values = new ArrayList<>();
        for (Exercise e : exercises) {
            Date date = e.getDate();
            int index = dates.indexOf(date);
            double quantity = Double.parseDouble(e.getQuantity().toString()) + values.get(index);
            values.set(index, quantity);
        }

        return values;
    }

    private ArrayList<Double> caloriesByDate(ArrayList<Exercise> exercises, ArrayList<Date> dates) {
        ArrayList<Double> values = new ArrayList<>();
        for (Exercise e : exercises) {
            Date date = e.getDate();
            int index = dates.indexOf(date);
            double calories = Double.parseDouble(e.getCalories().toString()) + values.get(index);
            values.set(index, calories);
        }

        return values;
    }
}
