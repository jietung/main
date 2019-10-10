package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.regime.Regime;
import seedu.exercise.model.regime.RegimeName;

public class AddRCommand extends AddCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds exercises to the regime list.";

    public static final String MESSAGE_SUCCESS_REGIME = "Added regime";

    private List<Index> toAddIndexes;
    private RegimeName name;

    public AddRCommand(List<Index> indexes, RegimeName name) {
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
                || (other instanceof AddRCommand // instanceof handles nulls
                && toAddIndexes.equals(((AddRCommand) other).toAddIndexes));
    }
}
