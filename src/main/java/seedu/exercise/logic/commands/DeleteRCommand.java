package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.regime.Regime;

public class DeleteRCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the regimes identified by the index number used in the displayed regime list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REGIME_SUCCESS = "Deleted Regime: %1$s";

    private final Index targetIndex;

    public DeleteRCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Regime> lastShownList = model.getFilteredRegimeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REGIME_DISPLAYED_INDEX);
        }

        Regime regimeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRegime(regimeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_REGIME_SUCCESS, regimeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRCommand) other).targetIndex)); // state check
    }
}
