package seedu.exercise.logic.parser;

import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CHART;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.exercise.logic.commands.statistic.Statistic;
import seedu.exercise.logic.commands.statistic.StatsCommand;
import seedu.exercise.model.property.Date;

public class StatsCommandParserTest {

    public static final String VALID_CHART = "linechart";
    public static final String VALID_END_DATE = "23/10/2019";
    public static final String VALID_START_DATE = "17/10/2019";
    public static final String VALID_STATISTIC_CATEGORY = "calories";

    public static final String CHART_DESC = " " + PREFIX_CHART + VALID_CHART;
    public static final String END_DATE_DESC = " " + PREFIX_END_DATE + VALID_END_DATE;
    public static final String START_DATE_DESC = " " + PREFIX_START_DATE + VALID_START_DATE;
    public static final String STATISTIC_CATEGORY_DESC = " " + PREFIX_CATEGORY + VALID_STATISTIC_CATEGORY;

    public static final String INVALID_CHART = "invalidchart";
    public static final String INVALID_END_DATE = "01/01/2019"; //invalid because is before start date
    public static final String INVALID_END_DATE_TOO_FAR_APART = "12/12/2019";
    public static final String INVALID_STATISTIC_CATEGORY = "invalidcalories";

    public static final String INVALID_CHART_DESC = " " + PREFIX_CHART + INVALID_CHART;
    public static final String INVALID_STATISTIC_CATEGORY_DESC = " " + PREFIX_CATEGORY + INVALID_STATISTIC_CATEGORY;
    public static final String INVALID_END_DATE_DESC = " " + PREFIX_END_DATE + INVALID_END_DATE;
    public static final String INVALID_END_DATE_TOO_FAR_APART_DESC = " " + PREFIX_END_DATE
            + INVALID_END_DATE_TOO_FAR_APART;

    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void allFieldsPresent_success() {
        StatsCommand expectedStatsCommand = new StatsCommand(VALID_CHART, VALID_STATISTIC_CATEGORY,
                new Date(VALID_START_DATE), new Date(VALID_END_DATE));

        assertParseSuccess(parser, CHART_DESC + STATISTIC_CATEGORY_DESC + START_DATE_DESC + END_DATE_DESC,
            expectedStatsCommand);
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        StatsCommand expectedStatsCommand = new StatsCommand(VALID_CHART, VALID_STATISTIC_CATEGORY, null, null);
        assertParseSuccess(parser, CHART_DESC + STATISTIC_CATEGORY_DESC, expectedStatsCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE);

        //missing chart prefix
        assertParseFailure(parser, VALID_CHART + STATISTIC_CATEGORY_DESC + START_DATE_DESC + END_DATE_DESC,
                expectedMessage);

        //missing category prefix
        assertParseFailure(parser, CHART_DESC + VALID_STATISTIC_CATEGORY + START_DATE_DESC + END_DATE_DESC,
                expectedMessage);

        //all prefix missing
        assertParseFailure(parser, VALID_CHART + VALID_STATISTIC_CATEGORY + VALID_START_DATE + VALID_END_DATE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid chart
        assertParseFailure(parser, INVALID_CHART_DESC + STATISTIC_CATEGORY_DESC
                + START_DATE_DESC + END_DATE_DESC, Statistic.MESSAGE_INVALID_CHART_TYPE);

        //invalid category
        assertParseFailure(parser, CHART_DESC + INVALID_STATISTIC_CATEGORY_DESC
                + START_DATE_DESC + END_DATE_DESC, Statistic.MESSAGE_INVALID_CATEGORY);

        //start date present, end date not present
        assertParseFailure(parser, CHART_DESC + STATISTIC_CATEGORY_DESC + START_DATE_DESC,
                StatsCommandParser.MESSAGE_INVALID_COMMAND);

        //end date present, start date not present
        assertParseFailure(parser, CHART_DESC + STATISTIC_CATEGORY_DESC + END_DATE_DESC,
                StatsCommandParser.MESSAGE_INVALID_COMMAND);

        //end date is before start date
        assertParseFailure(parser, CHART_DESC + STATISTIC_CATEGORY_DESC + START_DATE_DESC + INVALID_END_DATE_DESC,
                Date.MESSAGE_INVALID_END_DATE);

        //start date and end date too far apart
        assertParseFailure(parser, CHART_DESC + STATISTIC_CATEGORY_DESC + START_DATE_DESC
                + INVALID_END_DATE_TOO_FAR_APART_DESC, StatsCommandParser.MESSAGE_INVALID_DATE_RANGE);
    }
}
