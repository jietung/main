package seedu.exercise.model.regime;

import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.exercise.UniqueExerciseList;

public class Regime {
    private final RegimeName name;
    private final UniqueExerciseList exercises;

    public Regime(RegimeName name, UniqueExerciseList exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        exercises.remove(exercise);
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

    public UniqueExerciseList getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        String str = "";
        int i = 1;
        for (Exercise e : exercises) {
            str += "Exercise " + i + ": " + e.getName().toString() + "\n";
            i++;
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
               || (other instanceof Regime)
               && name.equals(((Regime) other).getRegimeName());
    }
}
