package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.regime.Regime;
import seedu.exercise.model.regime.RegimeName;

public class AddRegimeCommand extends AddCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds exercises to the regime list."
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_NAME + "REGIME NAME "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "regime "
            + PREFIX_NAME + "power set"
            + PREFIX_INDEX + "1 "
            + PREFIX_INDEX + "2";

    public static final String MESSAGE_SUCCESS_REGIME = "Added regime";

    private List<Index> toAddIndexes;
    private RegimeName name;

    public AddRegimeCommand(List<Index> indexes, RegimeName name) {
        requireAllNonNull(indexes, name);
        toAddIndexes = indexes;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Exercise> lastShownList = model.getFilteredExerciseList();

        //check all index valid
        for (Index targetIndex : toAddIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
            }
        }

        //add exercise to new regime
        Regime regime = new Regime(name, new ArrayList<>());
        for (Index index : toAddIndexes) {
            regime.addExercise(lastShownList.get(index.getZeroBased()));
        }

        model.addRegime(regime);
        return new CommandResult(MESSAGE_SUCCESS_REGIME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRegimeCommand // instanceof handles nulls
                && toAddIndexes.equals(((AddRegimeCommand) other).toAddIndexes));
    }
}
