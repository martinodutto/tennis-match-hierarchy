package set;

import exceptions.ValidationException;
import game.CompletedGameScores;
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

    public final long getScoreForFirstPlayer() {
        return games
                .stream()
                .filter(Game::ended)
                .filter(g -> g.getScore1() == CompletedGameScores.WON)
                .count();
    }

    public final long getScoreForSecondPlayer() {
        return games
                .stream()
                .filter(Game::ended)
                .filter(g -> g.getScore2() == CompletedGameScores.WON)
                .count();
    }

    public final boolean wonByFirstPlayer() {
        if (ended()) {
            return getScoreForFirstPlayer() > getScoreForSecondPlayer();
        } else {
            throw new IllegalStateException("Unterminated set. A winner can't be determined");
        }
    }

    public final boolean wonBySecondPlayer() {
        if (ended()) {
            return getScoreForSecondPlayer() > getScoreForFirstPlayer();
        } else {
            throw new IllegalStateException("Unterminated set. A winner can't be determined");
        }
    }

    public final java.util.Set<Game> getGames() {
        return games;
    }

    protected boolean allGamesEnded() {
        return games.stream().allMatch(Game::ended);
    }

    /**
     * Checks whether this set contains at most one game which is unterminated.
     *
     * @return True iff there is at most one unterminated game.
     */
    protected boolean atMostOneGameIsNonEnded() {
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
