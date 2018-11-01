package match;

import exceptions.ValidationException;
import interfaces.Terminable;
import interfaces.Validable;
import interfaces.Winnable;
import set.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

public abstract class Match implements Terminable, Validable, Winnable {

    private final List<Set> sets;

    Match(List<Set> sets) throws ValidationException {
        this.sets = unmodifiableList(Optional.ofNullable(sets).orElse(new ArrayList<>()));
        validate();
    }

    public final long getScoreForFirstPlayer() {
        return sets
                .stream()
                .filter(Set::terminated)
                .filter(Set::wonByFirstPlayer)
                .count();
    }

    public final long getScoreForSecondPlayer() {
        return sets
                .stream()
                .filter(Set::terminated)
                .filter(Set::wonBySecondPlayer)
                .count();
    }

    @Override
    public final boolean wonByFirstPlayer() {
        if (terminated()) {
            return getScoreForFirstPlayer() > getScoreForSecondPlayer();
        } else {
            throw new IllegalStateException("Unterminated match. A winner can't be determined");
        }
    }

    @Override
    public final boolean wonBySecondPlayer() {
        if (terminated()) {
            return getScoreForSecondPlayer() > getScoreForFirstPlayer();
        } else {
            throw new IllegalStateException("Unterminated match. A winner can't be determined");
        }
    }

    public final List<Set> getSets() {
        return sets;
    }

    boolean allSetsTerminated() {
        return sets.stream().allMatch(Set::terminated);
    }

    /**
     * Checks whether there is any set, strictly before the last, that is unterminated.
     * If this happens, the set must be considered invalid.
     *
     * @return True iff the only (eventual) unterminated set is the last one.
     */
    private boolean firstNonTerminatedSetIsTheLast() {
        final Optional<Set> firstNonTerminatedSet = getSets()
                .stream()
                .filter(s -> !s.terminated())
                .findFirst();

        return firstNonTerminatedSet
                .map(s -> getSets().indexOf(s) == getSets().size() - 1)
                .orElse(true);
    }

    @Override
    public void validate() throws ValidationException {
        if (!firstNonTerminatedSetIsTheLast()) {
            throw new ValidationException("All sets must be completed, with the exception of the last one");
        }
    }
}
