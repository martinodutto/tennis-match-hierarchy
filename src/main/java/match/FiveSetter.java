package match;

import exceptions.ValidationException;
import set.Set;

import java.util.List;

public class FiveSetter extends Match {

    private FiveSetter(List<Set> sets) throws ValidationException {
        super(sets);
    }

    static FiveSetter ofSets(List<Set> sets) throws ValidationException {
        return new FiveSetter(sets);
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (getSets().size() == 0 || getSets().size() > 5) {
            throw new ValidationException("Invalid number of sets for a five-setter: " + getSets().size());
        }
        if (getScoreForFirstPlayer() >= 4 || getScoreForSecondPlayer() >= 4) {
            throw new ValidationException("No player can win more than 3 sets on a five-setter");
        }
    }

    @Override
    public boolean terminated() {
        return allSetsTerminated() &&
                (getScoreForFirstPlayer() == 3 || getScoreForSecondPlayer() == 3);
    }
}
