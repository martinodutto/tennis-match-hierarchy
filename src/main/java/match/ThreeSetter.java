package match;

import exceptions.ValidationException;
import set.Set;

import java.util.List;

public class ThreeSetter extends Match {

    private ThreeSetter(List<Set> sets) throws ValidationException {
        super(sets);
    }

    static ThreeSetter ofSets(List<Set> sets) throws ValidationException {
        return new ThreeSetter(sets);
    }

    @Override
    public void validate() throws ValidationException {
        if (getSets().size() == 0 || getSets().size() > 3) {
            throw new ValidationException("Invalid number of sets for a three-setter: " + getSets().size());
        }
        if (getScoreForFirstPlayer() == 3 || getScoreForSecondPlayer() == 3) {
            throw new ValidationException("No player can win 3 sets on a three-setter");
        }
        if (!firstNonTerminatedSetIsTheLast()) {
            throw new ValidationException("All sets must be completed, with the exception of the last one");
        }
    }

    @Override
    public boolean terminated() {
        return allSetsTerminated() &&
                (getScoreForFirstPlayer() == 2 || getScoreForSecondPlayer() == 2);
    }
}
