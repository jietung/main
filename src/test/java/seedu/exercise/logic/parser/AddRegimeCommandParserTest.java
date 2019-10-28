package seedu.exercise.logic.parser;

import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.exercise.testutil.typicalutil.TypicalRegime.VALID_REGIME_INDEXES;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.AddRegimeCommand;
import seedu.exercise.model.property.Name;

public class AddRegimeCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, " t/regime n/Level 1 i/1",
                new AddRegimeCommand(VALID_REGIME_INDEXES, new Name("Level 1")));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        assertParseSuccess(parser, " t/regime n/Level 1",
                new AddRegimeCommand(new ArrayList<>(), new Name("Level 1")));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRegimeCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " t/regime i/1", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " t/regime n/test i/a", Index.MESSAGE_CONSTRAINTS);
    }
}
