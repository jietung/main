package seedu.exercise.model.regime;

import java.util.List;

import seedu.exercise.model.exercise.Exercise;

public class Regime {
    public RegimeName name;
    public List<Exercise> exercises;

    public Regime(RegimeName name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public RegimeName getRegimeName() {
        return name;
    }

    public boolean isSameRegime(Regime regime) {
        if (name.equals(regime.getRegimeName())) {
            return true;
        }
        return false;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        String str = "";
        for (Exercise e : exercises) {
            str += " " + e.getName().toString() + "\n";
        }
        return str;
    }
}
