package set;

import exceptions.ValidationException;
import game.Game;
import interfaces.Abortable;
import interfaces.Validable;

import java.util.HashSet;
import java.util.Optional;

import static java.util.Collections.unmodifiableSet;

public abstract class Set implements Abortable, Validable {

    private final java.util.Set<Game> games;

    Set(java.util.Set<Game> games) throws ValidationException {
        this.games = unmodifiableSet(Optional.ofNullable(games).orElse(new HashSet<>()));
        validate();
    }

    /**
     * Counts the number of <em>completed</em> games won by the first player, for the current set.
     *
     * @return Number of completed games won by the first player.
     */
    public final long getScoreForFirstPlayer() {
        return games
                .stream()
                .filter(Game::ended)
                .filter(Game::wonByFirstPlayer)
                .count();
    }

    /**
     * Counts the number of <em>completed</em> games won by the second player, for the current set.
     *
     * @return Number of completed games won by the second player.
     */
    public final long getScoreForSecondPlayer() {
        return games
                .stream()
                .filter(Game::ended)
                .filter(Game::wonBySecondPlayer)
                .count();
    }

    /**
     * If the set has completed, returns {@code true} when the winner is the first player.
     *
     * <p>This method must be called <em>after</em> verifying that the current set has completed (see {@link #ended()}),
     * otherwise it will throw an {@link IllegalStateException}, because no winner can be determined.</p>
     *
     * @return True iff the winner exists and is the first player.
     */
    public final boolean wonByFirstPlayer() {
        if (ended()) {
            return getScoreForFirstPlayer() > getScoreForSecondPlayer();
        } else {
            throw new IllegalStateException("Unterminated set. A winner can't be determined");
        }
    }

    /**
     * If the set has completed, returns {@code true} when the winner is the second player.
     *
     * <p>This method must be called <em>after</em> verifying that the current set has completed (see {@link #ended()}),
     * otherwise it will throw an {@link IllegalStateException}, because no winner can be determined.</p>
     *
     * @return True iff the winner exists and is the second player.
     */
    public final boolean wonBySecondPlayer() {
        if (ended()) {
            return getScoreForSecondPlayer() > getScoreForFirstPlayer();
        } else {
            throw new IllegalStateException("Unterminated set. A winner can't be determined");
        }
    }

    final java.util.Set<Game> getGames() {
        return games;
    }

    boolean allGamesEnded() {
        return games.stream().allMatch(Game::ended);
    }

    /**
     * Checks whether this set contains at most one game which is unterminated.
     *
     * @return True iff there is at most one unterminated game.
     */
    boolean atMostOneGameIsNonEnded() {
        return getGames()
                .stream()
                .filter(g -> !g.ended())
                .count() <= 1;
    }

    @Override
    public String toString() {
        return getScoreForFirstPlayer() + " - " + getScoreForSecondPlayer();
    }
}
