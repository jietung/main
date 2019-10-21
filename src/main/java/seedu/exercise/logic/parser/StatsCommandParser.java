package seedu.exercise.logic.parser;

import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CHART;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.stream.Stream;

import seedu.exercise.logic.commands.StatsCommand;
import seedu.exercise.logic.parser.exceptions.ParseException;
import seedu.exercise.model.property.Date;

/**
 * Parses input arguments and creates a new StatsCommand object.
 */
public class StatsCommandParser implements Parser<StatsCommand> {

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
        if (arePrefixesPresent(argMultimap, PREFIX_STARTDATE, PREFIX_ENDDATE)) { //both dates present

            startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());
            endDate = ParserUtil.parseEndDate(startDate, argMultimap.getValue(PREFIX_ENDDATE).get());

        } else if (arePrefixesPresent(argMultimap, PREFIX_STARTDATE)
            && !arePrefixesPresent(argMultimap, PREFIX_ENDDATE)) {   //only start date present

            throw new ParseException("End date must be provided.");

        } else if (arePrefixesPresent(argMultimap, PREFIX_ENDDATE)
            && !arePrefixesPresent(argMultimap, PREFIX_STARTDATE)) {  //only end date present

            throw new ParseException("Start date must be provided.");

        }

        return new StatsCommand(chart, category, startDate, endDate);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
