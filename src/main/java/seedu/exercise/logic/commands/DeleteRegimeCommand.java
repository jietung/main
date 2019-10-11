package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.exercise.UniqueExerciseList;
import seedu.exercise.model.regime.Regime;
import seedu.exercise.model.regime.RegimeName;

public class DeleteRegimeCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the regimes identified by the name used in the displayed regime list.\n"
            + "or Deletes the exercise identified by index in the list of input regime name.\n "
            + "Parameters: REGIME NAME, INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + "regime "
            + PREFIX_NAME + "level 1 " + PREFIX_INDEX + "1";

    public static final String MESSAGE_DELETE_REGIME_SUCCESS = "Deleted Regime: %1$s";
    private static final String MESSAGE_REGIME_DOES_NOT_EXIST = "No such regime in regime book.";
    private static final String MESSAGE_DELETE_EXERCISE_IN_REGIME_SUCCESS = "Deleted exercises in regime.";

    private final List<Index> indexes;
    private final RegimeName name;

    public DeleteRegimeCommand(RegimeName name, List<Index> indexes) {
        this.name = name;
        this.indexes = indexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Regime> lastShownList = model.getFilteredRegimeList();
        Regime regime = new Regime(name, new UniqueExerciseList());

        if (!model.hasRegime(regime)) {
            throw new CommandException(MESSAGE_REGIME_DOES_NOT_EXIST);
        }

        int indexOfRegime = model.getRegimeIndex(regime);
        Regime regimeToDelete = lastShownList.get(indexOfRegime);

        //no index provided, delete regime
        if (indexes == null) {

            model.deleteRegime(regimeToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_REGIME_SUCCESS, regimeToDelete));

        } else { //index provided, delete exercise in regime

            List<Exercise> currentExerciseList = regimeToDelete.getExercises().asUnmodifiableObservableList();
            //check all index valid
            for (Index targetIndex : indexes) {
                if (targetIndex.getZeroBased() >= currentExerciseList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
                }
            }

            // delete exercise identified by index
            for (Index targetIndex : indexes) {
                Exercise exerciseToDelete = currentExerciseList.get(targetIndex.getZeroBased());
                regimeToDelete.deleteExercise(exerciseToDelete);
            }

            model.setRegime(regime, regimeToDelete);
            model.updateFilteredRegimeList(Model.PREDICATE_SHOW_ALL_REGIMES);
            return new CommandResult(String.format(MESSAGE_DELETE_EXERCISE_IN_REGIME_SUCCESS, regimeToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRegimeCommand // instanceof handles nulls
                && indexes.equals(((DeleteRegimeCommand) other).indexes)); // state check
    }
}
