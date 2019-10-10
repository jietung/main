package seedu.exercise.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.exercise.commons.exceptions.IllegalValueException;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.model.regime.Regime;
import seedu.exercise.model.regime.RegimeName;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly version of {@link Regime}.
 */
public class JsonAdaptedRegime {

    private final String name;
    private final List<JsonAdaptedExercise> exercises = new ArrayList<>();

    public JsonAdaptedRegime(@JsonProperty("name") String name,
                             @JsonProperty("exercises") List<JsonAdaptedExercise> exercises) {
        this.name = name;
        if (exercises != null) {
            this.exercises.addAll(exercises);
        }
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedRegime(Regime source) {
        name = source.getRegimeName().toString();
        exercises.addAll(source.getExercises().stream()
                .map(JsonAdaptedExercise::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted regime object into the model's {@code Regime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted regime.
     */
    public Regime toModelType() throws IllegalValueException {
        final List<Exercise> eList = new ArrayList<>();
        for (JsonAdaptedExercise exercise : exercises) {
            eList.add(exercise.toModelType());
        }

        final RegimeName modelName = new RegimeName(name);
        final List<Exercise> modelExercises = new ArrayList<>(eList);
        return new Regime(modelName, modelExercises);
    }
}
