package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.ArrayList;
import java.util.List;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.regime.Regime;

/**
 * Adds an exercise to the exercise book.
 */
public class AddECommand extends AddCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a exercise to the exercise list. "
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_NAME + "EXERCISE NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_CALORIES + "CALORIES "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_UNIT + "UNITS "
            + "[" + PREFIX_MUSCLE + "MUSCLE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "exercise"
            + PREFIX_NAME + "Run "
            + PREFIX_DATE + "22/09/2019 "
            + PREFIX_CALORIES + "1500 "
            + PREFIX_QUANTITY + "2.4 "
            + PREFIX_UNIT + "km "
            + PREFIX_MUSCLE + "Leg";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise book";

    private Exercise toAdd;

    /**
     * Creates an AddECommand to add the specified {@code Exercise}
     */
    public AddECommand(Exercise exercise) {
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
                || (other instanceof AddECommand // instanceof handles nulls
                && toAdd.equals(((AddECommand) other).toAdd));
    }
}
