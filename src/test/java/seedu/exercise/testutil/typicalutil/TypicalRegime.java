package seedu.exercise.testutil.typicalutil;

import static seedu.exercise.testutil.typicalutil.TypicalIndexes.INDEX_ONE_BASED_FIRST;
import static seedu.exercise.testutil.typicalutil.TypicalIndexes.INDEX_VERY_LARGE_NUMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.exercise.commons.core.index.Index;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.UniqueResourceList;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.testutil.builder.RegimeBuilder;

/**
 * Holds some regime objects that can be used in testing.
 */
public class TypicalRegime {

    public static final String VALID_REGIME_NAME_CARDIO = "cardio";
    public static final String VALID_REGIME_NAME_LEGS = "legs";
    public static final String VALID_REGIME_NAME_CHEST = "chest";

    public static final ArrayList<Index> VALID_REGIME_INDEXES = new ArrayList<>(Arrays.asList(INDEX_ONE_BASED_FIRST));
    public static final ArrayList<Index> DUPLICATE_REGIME_INDEXES = new ArrayList<>(Arrays.asList(INDEX_ONE_BASED_FIRST,
            INDEX_ONE_BASED_FIRST));
    public static final ArrayList<Index> LARGE_REGIME_INDEX = new ArrayList<>(Arrays.asList(INDEX_VERY_LARGE_NUMBER));

    public static final Regime VALID_REGIME_LEGS;
    public static final Regime VALID_REGIME_CARDIO;

    static {
        List<Exercise> typicalExerciseCardio = TypicalExercises.getTypicalExercises();
        UniqueResourceList<Exercise> cardioList = new UniqueResourceList<>();
        cardioList.setAll(typicalExerciseCardio);

        List<Exercise> typicalExerciseLegs = new ArrayList<>();
        typicalExerciseCardio.add(TypicalExercises.SPRINT);
        typicalExerciseCardio.add(TypicalExercises.WALK);
        UniqueResourceList<Exercise> legList = new UniqueResourceList<>();
        legList.setAll(typicalExerciseLegs);

        VALID_REGIME_CARDIO = new RegimeBuilder()
                .withName(VALID_REGIME_NAME_CARDIO)
                .withExerciseList(cardioList)
                .build();

        VALID_REGIME_LEGS = new RegimeBuilder()
                .withName(VALID_REGIME_NAME_LEGS)
                .withExerciseList(legList)
                .build();

    }

    private TypicalRegime() {}

    public static ReadOnlyResourceBook<Regime> getTypicalRegimeBook() {
        ReadOnlyResourceBook<Regime> eb = new ReadOnlyResourceBook<>();
        for (Regime regime : getValidRegimeList()) {
            eb.addResource(regime);
        }
        return eb;
    }


    public static List<Regime> getValidRegimeList() {
        return Arrays.asList(VALID_REGIME_CARDIO, VALID_REGIME_LEGS);
    }
}
