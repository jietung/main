package seedu.exercise.model.regime.exceptions;

public class DuplicateRegimeException extends RuntimeException {
    public DuplicateRegimeException() {
        super("Operation would result in duplicate regimes");
    }
}
