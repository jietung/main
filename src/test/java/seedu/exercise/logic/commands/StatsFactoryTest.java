package seedu.exercise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.exercise.logic.commands.statistic.Statistic;
import seedu.exercise.logic.commands.statistic.StatsFactory;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.property.Date;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.testutil.ExerciseBookBuilder;
import seedu.exercise.testutil.StatisticBuilder;
import seedu.exercise.testutil.exercise.ExerciseBuilder;

public class StatsFactoryTest {

    private ReadOnlyResourceBook<Exercise> eb;

    @BeforeEach
    public void setUp() {
        Exercise running = new ExerciseBuilder().build(); //Running, 111 kcal, 0.5 hours
        Exercise swimming = new ExerciseBuilder().withName("Swimming").withDate("27/09/2019")
                .withCalories("300").withQuantity("10").withUnit("laps").build();
        Exercise cycling = new ExerciseBuilder().withName("Cycling").withDate("28/09/2019")
                .withCalories("150").withQuantity("5").withUnit("km").build();
        Exercise anotherRunning = new ExerciseBuilder().withDate("28/09/2019").withQuantity("1.0").build();
        eb = new ExerciseBookBuilder().withExercise(running).withExercise(swimming)
                .withExercise(cycling).withExercise(anotherRunning).build();
    }

    @Test
    public void generateLineChartStatistic() {
        String[] dates = new String[] {"25/09/2019", "26/09/2019", "27/09/2019", "28/09/2019",
                "29/09/2019", "30/09/2019"};
        ArrayList<String> expectedLineChartProperties = new ArrayList<>(Arrays.asList(dates));
        //======calories================================================================================================
        StatsFactory caloriesStatsFactory = new StatsFactory(eb, "linechart", "calories",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualCaloriesStatistic = caloriesStatsFactory.generateStatistic();

        Double[] caloriesArr = new Double[] {0.0, 111.0, 300.0, 261.0, 0.0, 0.0};
        ArrayList<Double> expectedCaloriesValues = new ArrayList<>(Arrays.asList(caloriesArr));

        Statistic expectedCaloriesStatistic = new StatisticBuilder().withCategory("calories").withChart("linechart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedLineChartProperties).withValues(expectedCaloriesValues).build();

        assertEquals(expectedCaloriesStatistic, actualCaloriesStatistic);

        //======exercise================================================================================================
        StatsFactory exerciseStatsFactory = new StatsFactory(eb, "linechart", "exercise",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualExerciseStatistic = exerciseStatsFactory.generateStatistic();

        Double[] valuesArr = new Double[] {0.0, 1.0, 1.0, 2.0, 0.0, 0.0};
        ArrayList<Double> expectedExerciseValues = new ArrayList<>(Arrays.asList(valuesArr));

        Statistic expectedExerciseStatistic = new StatisticBuilder().withCategory("exercise").withChart("linechart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedLineChartProperties).withValues(expectedExerciseValues).build();

        assertEquals(expectedExerciseStatistic, actualExerciseStatistic);

    }

    @Test
    public void generateBarChartStatistic() {
        String[] propertiesArr = new String[] {"Cycling (km)", "Running (hours)", "Swimming (laps)"};
        ArrayList<String> expectedProperties = new ArrayList<>(Arrays.asList(propertiesArr));
        //=====calories=================================================================================================
        StatsFactory caloriesStatsFactory = new StatsFactory(eb, "barchart", "calories",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualCaloriesStatistic = caloriesStatsFactory.generateStatistic();

        Double[] caloriesArr = new Double[] {150.0, 222.0, 300.0};
        ArrayList<Double> expectedCalories = new ArrayList<>(Arrays.asList(caloriesArr));

        Statistic expectedCaloriesStatistic = new StatisticBuilder().withCategory("calories").withChart("barchart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedProperties).withValues(expectedCalories).build();

        assertEquals(expectedCaloriesStatistic, actualCaloriesStatistic);

        //=====exercise=================================================================================================
        StatsFactory exerciseStatsFactory = new StatsFactory(eb, "barchart", "exercise",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualExerciseStatistic = exerciseStatsFactory.generateStatistic();

        Double[] valuesArr = new Double[] {5.0, 1.5, 10.0};
        ArrayList<Double> expectedValues = new ArrayList<>(Arrays.asList(valuesArr));

        Statistic expectedExerciseStatistic = new StatisticBuilder().withCategory("exercise").withChart("barchart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedProperties).withValues(expectedValues).build();

        assertEquals(expectedExerciseStatistic, actualExerciseStatistic);
    }

    @Test
    public void generatePieChartStatistic() {
        String[] propertiesArr = new String[] {"Cycling (km)", "Running (hours)", "Swimming (laps)"};
        ArrayList<String> expectedProperties = new ArrayList<>(Arrays.asList(propertiesArr));
        //=====calories=================================================================================================
        StatsFactory caloriesStatsFactory = new StatsFactory(eb, "piechart", "calories",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualCaloriesStatistic = caloriesStatsFactory.generateStatistic();

        Double[] caloriesArr = new Double[] {150.0, 222.0, 300.0};
        ArrayList<Double> expectedCalories = new ArrayList<>(Arrays.asList(caloriesArr));

        Statistic expectedCaloriesStatistic = new StatisticBuilder().withCategory("calories").withChart("piechart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedProperties).withValues(expectedCalories).build();

        assertEquals(expectedCaloriesStatistic, actualCaloriesStatistic);

        //=====exercise=================================================================================================
        StatsFactory exerciseStatsFactory = new StatsFactory(eb, "piechart", "exercise",
                new Date("25/09/2019"), new Date("30/09/2019"));
        Statistic actualExerciseStatistic = exerciseStatsFactory.generateStatistic();

        Double[] valuesArr = new Double[] {1.0, 2.0, 1.0};
        ArrayList<Double> expectedValues = new ArrayList<>(Arrays.asList(valuesArr));

        Statistic expectedExerciseStatistic = new StatisticBuilder().withCategory("exercise").withChart("piechart")
                .withStartDate(new Date("25/09/2019")).withEndDate(new Date("30/09/2019"))
                .withProperties(expectedProperties).withValues(expectedValues).build();

        assertEquals(expectedExerciseStatistic, actualExerciseStatistic);
    }
}
