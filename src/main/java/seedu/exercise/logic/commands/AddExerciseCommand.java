package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.exercise.Exercise;

/**
 * Adds an exercise to the exercise book.
 */
public class AddExerciseCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise book";

    private Exercise toAdd;

    /**
     * Creates an AddExerciseCommand to add the specified {@code Exercise}
     */
    public AddExerciseCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExerciseCommand // instanceof handles nulls
                && toAdd.equals(((AddExerciseCommand) other).toAdd));
    }
}
