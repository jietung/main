package seedu.exercise.model.regime;

/**
 * Represents a Regime's name.
 */
public class RegimeName {
    public final String name;

    public RegimeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegimeName // instanceof handles nulls
                && name.equals(((RegimeName) other).name)); // state check
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
