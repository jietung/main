package seedu.exercise.model;

import javafx.collections.ObservableList;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.regime.Regime;

/**
 * Unmodifiable view of an exercise book
 */
public interface ReadOnlyExerciseBook {

    /**
     * Returns an unmodifiable view of the exercises list.
     * This list will not contain any duplicate exercises.
     */
    ObservableList<Exercise> getExerciseList();

    /**
     * Returns an unmodifiable view of the regimes list.
     * This list will not contain any duplicate regimes.
     */
    ObservableList<Regime> getRegimeList();

}
