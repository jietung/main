package seedu.exercise.model.regime;

import java.util.ArrayList;

import seedu.exercise.model.exercise.Exercise;

public class Regime {
    private final RegimeName name;
    private final ArrayList<Exercise> exercises;

    public Regime(RegimeName name, ArrayList<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public RegimeName getRegimeName() {
        return name;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
