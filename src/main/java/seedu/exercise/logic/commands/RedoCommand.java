package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.exercise.logic.commands.events.Event;
import seedu.exercise.logic.commands.events.EventHistory;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.logic.commands.statistic.Statistic;
import seedu.exercise.logic.commands.statistic.StatsFactory;
import seedu.exercise.model.Model;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.resource.Exercise;

/**
 * Undoes the last executed command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Command redone: \n%1$s";
    public static final String MESSAGE_EMPTY_REDO_STACK = "There is no command to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        EventHistory eventHistory = EventHistory.getInstance();

        if (eventHistory.isRedoStackEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_REDO_STACK);
        }

        Event eventToRedo = eventHistory.redo(model);
        updateStatistic(model);
        return new CommandResult(
            String.format(MESSAGE_SUCCESS, eventToRedo));
    }

    /**
     * Update statistic with updated model.
     */
    private void updateStatistic(Model model) {
        ReadOnlyResourceBook<Exercise> exercises = model.getExerciseBookData();
        Statistic outdatedStatistic = model.getStatistic();
        StatsFactory statsFactory = new StatsFactory(exercises, outdatedStatistic.getChart(),
                outdatedStatistic.getCategory(), outdatedStatistic.getStartDate(), outdatedStatistic.getEndDate());
        Statistic statistic = statsFactory.generateStatistic();
        model.updateStatistic(statistic);
    }

}
