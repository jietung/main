package seedu.exercise.logic.commands.statistic;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CHART;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_STARTDATE;

import seedu.exercise.logic.commands.Command;
import seedu.exercise.logic.commands.CommandResult;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.property.Date;
import seedu.exercise.model.resource.Exercise;

public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = "Parameters: "
        + PREFIX_CATEGORY + "CATEGORY "
        + PREFIX_CHART + "CHART TYPE "
        + PREFIX_STARTDATE + "START DATE "
        + PREFIX_ENDDATE + "END DATE " + "\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_CATEGORY + "calories "
        + PREFIX_CHART + "barchart "
        + PREFIX_STARTDATE + "30/03/2019 "
        + PREFIX_ENDDATE + "05/04/2019 ";

    private String chart;
    private String category;
    private Date startDate;
    private Date endDate;

    public StatsCommand(String chart, String category, Date startDate, Date endDate) {
        this.chart = chart;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyResourceBook<Exercise> exercises = model.getExerciseBookData();
        StatsFactory statsFactory = new StatsFactory(exercises, chart, category, startDate, endDate);
        Statistic statistic = statsFactory.generateStatistic();
        model.setStatistic(statistic);
        return new CommandResult("Chart displayed");
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
