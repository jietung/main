package seedu.exercise.logic.parser;

import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CHART;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_STARTDATE;

import seedu.exercise.logic.commands.statistic.StatsCommand;
import seedu.exercise.logic.parser.exceptions.ParseException;
import seedu.exercise.model.property.Date;

/**
 * Parses input arguments and creates a new StatsCommand object.
 */
public class StatsCommandParser implements Parser<StatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_CHART,
            PREFIX_STARTDATE, PREFIX_ENDDATE);

        if (!argMultimap.arePrefixesPresent(PREFIX_CATEGORY, PREFIX_CHART) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        }

        String category = ParserUtil.parseStatisticCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        String chart = ParserUtil.parseChart(argMultimap.getValue(PREFIX_CHART).get());
        Date startDate = null;
        Date endDate = null;

        //date provided
        if (argMultimap.arePrefixesPresent(PREFIX_STARTDATE, PREFIX_ENDDATE)) { //both dates present

            startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());
            endDate = ParserUtil.parseEndDate(startDate, argMultimap.getValue(PREFIX_ENDDATE).get());

            int numberOfDaysApart = Date.numberOfDaysBetween(startDate, endDate);

            if (numberOfDaysApart > 31) {
                throw new ParseException("Start date and end date are too far apart");
            }

        } else if (argMultimap.arePrefixesPresent(PREFIX_STARTDATE)
                && !argMultimap.arePrefixesPresent(PREFIX_ENDDATE)) { //only start date present

            throw new ParseException("End date must be provided.");

        } else if (argMultimap.arePrefixesPresent(PREFIX_ENDDATE)
                && !argMultimap.arePrefixesPresent(PREFIX_STARTDATE)) { //only end date present

            throw new ParseException("Start date must be provided.");

        }

        return new StatsCommand(chart, category, startDate, endDate);
    }
}
