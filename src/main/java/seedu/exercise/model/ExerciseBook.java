package seedu.exercise.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.exercise.UniqueExerciseList;
import seedu.exercise.model.regime.Regime;
import seedu.exercise.model.regime.UniqueRegimeList;

/**
 * Wraps all data at the exercise-book level
 * Duplicates are not allowed (by .isSameExercise comparison)
 */
public class ExerciseBook implements ReadOnlyExerciseBook {

    private final UniqueExerciseList exercises;
    private final UniqueRegimeList regimes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        exercises = new UniqueExerciseList();
        regimes = new UniqueRegimeList();
    }

    public ExerciseBook() {}

    /**
     * Creates an ExerciseBook using the Exercises in the {@code toBeCopied}
     */
    public ExerciseBook(ReadOnlyExerciseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    public void setRegimes(List<Regime> regimes) {
        this.regimes.setRegimes(regimes);
    }

    /**
     * Resets the existing data of this {@code ExerciseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyExerciseBook newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());

        setRegimes(newData.getRegimeList());
    }

    //// exercise-level operations

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the exercise book.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Adds an exercise to the exercise book.
     * The exercise must not already exist in the exercise book.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the exercise book.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in the
     * exercise book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code ExerciseBook}.
     * {@code key} must exist in the exercise book.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// regime-level operations

    /**
     * Returns true if a regime with the same name exists in the regime book.
     */
    public boolean hasRegime(Regime regime) {
        requireNonNull(regime);
        return regimes.contains(regime);
    }

    /**
     * Adds a regime to the regime book.
     * THe regime must not already exist in the exercise book.
     */
    public void addRegime(Regime r) {
        regimes.add(r);
    }

    public void setRegime(Regime target, Regime editedRegime) {
        requireNonNull(editedRegime);

        regimes.setRegime(target, editedRegime);
    }

    public void removeRegime(Regime key) {
        regimes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exercises.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Regime> getRegimeList() {
        return regimes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseBook // instanceof handles nulls
                && exercises.equals(((ExerciseBook) other).exercises));
    }

    @Override
    public int hashCode() {
        return exercises.hashCode();
    }
}
