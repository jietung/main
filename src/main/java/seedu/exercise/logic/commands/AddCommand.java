package seedu.exercise.logic.commands;

import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_UNIT;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE_EXERCISE = "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_NAME + "EXERCISE NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_CALORIES + "CALORIES "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_UNIT + "UNITS "
            + "[" + PREFIX_MUSCLE + "MUSCLE]...\n"
            + "\t\tExample: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "exercise"
            + PREFIX_NAME + "Run "
            + PREFIX_DATE + "22/09/2019 "
            + PREFIX_CALORIES + "1500 "
            + PREFIX_QUANTITY + "2.4 "
            + PREFIX_UNIT + "km "
            + PREFIX_MUSCLE + "Leg";

    public static final String MESSAGE_USAGE_REGIME = "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_NAME + "REGIME NAME "
            + PREFIX_INDEX + "INDEX\n"
            + "\t\tExample: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "regime "
            + PREFIX_NAME + "power set"
            + PREFIX_INDEX + "1 "
            + PREFIX_INDEX + "2";


    public final static String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds exercise to exercise list or adds regime to regime list.\n"
            + "EXERCISE: " + MESSAGE_USAGE_EXERCISE + "\n"
            + "REGIME: " + MESSAGE_USAGE_REGIME;
}
