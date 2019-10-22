package seedu.exercise.logic.commands.statistic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
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

    public Statistic generateStatistic() {
        HashMap<Name, Double> data;
        if (chart.equals("linechart")) {
            ArrayList<Date> dates = Date.getListOfDates(startDate, endDate);
            ArrayList<Double> values;

            if (category.equals("exercies")) {
                values = exerciseQuantityByDate(getFilteredExercise(), dates);
            } else {
                values = caloriesByDate(getFilteredExercise(), dates);
            }

            return new Statistic(category, chart, datesToString(dates), values);

        } else {
            if (category.equals("exercise")) {
                data = getTotalExerciseQuantity();
            } else { //calories
                data = getTotalCaloriesData();
            }

            ArrayList<Name> names = hashMapNameToList(data);
            ArrayList<Double> values = hashMapDoubleToList(data, names);

            if (chart.equals("piechart")) {
                return new Statistic(category, chart, namesToString(names), values);
            } else { //barchart
                return new Statistic(category, chart, namesToString(names), values);
            }
        }
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

    private ArrayList<String> namesToString(ArrayList<Name> names) {
        ArrayList<String> list = new ArrayList<>();
        for (Name n : names) {
            list.add(n.toString());
        }
        return list;
    }

    private ArrayList<String> datesToString(ArrayList<Date> dates) {
        ArrayList<String> list = new ArrayList<>();
        for (Date d : dates) {
            list.add(d.toString());
        }
        return list;
    }



    private ArrayList<Double> exerciseQuantityByDate(ArrayList<Exercise> exercises, ArrayList<Date> dates) {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i< dates.size(); i++) {
            values.add(0.0);
        }
        for (Exercise e : exercises) {
            Date date = e.getDate();
            int index = dates.indexOf(date);
            double quantity = values.get(index) + 1;
            values.set(index, quantity);
        }

        return values;
    }

    private ArrayList<Double> caloriesByDate(ArrayList<Exercise> exercises, ArrayList<Date> dates) {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i< dates.size(); i++) {
            values.add(0.0);
        }
        for (Exercise e : exercises) {
            Date date = e.getDate();
            int index = dates.indexOf(date);
            double calories = Double.parseDouble(e.getCalories().toString()) + values.get(index);
            values.set(index, calories);
        }

        return values;
    }
}
