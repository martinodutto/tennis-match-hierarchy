package set;

import exceptions.ValidationException;
import game.Game;

public class DistanceSet extends Set {

    private DistanceSet(java.util.Set<Game> games) throws ValidationException {
        super(games);
    }

    static DistanceSet ofGames(java.util.Set<Game> games) throws ValidationException {
        return new DistanceSet(games);
    }

    @Override
    public boolean ended() {
        long score1 = getScoreForFirstPlayer();
        long score2 = getScoreForSecondPlayer();

        return allGamesEnded() &&
                (endedNormally(score1, score2) || endedAtDistance(score1, score2));
    }

    private boolean endedNormally(long score1, long score2) {
        return (score1 == 7 && score2 == 5) || (score1 == 5 && score2 == 7) ||
                (score1 == 6 && score2 <= 4) || (score1 <= 4 && score2 == 6);
    }

    private boolean endedAtDistance(long score1, long score2) {
        return (score1 >= 6 && score2 >= 6) && (Math.abs(score1 - score2) == 2);
    }

    @Override
    public void validate() throws ValidationException {
        java.util.Set<Game> games = getGames();
        if (games.size() == 0) {
            throw new ValidationException("A distance set must have more than 1 game");
        }
        long score1 = getScoreForFirstPlayer();
        long score2 = getScoreForSecondPlayer();
        if (abnormalScore(score1, score2)) {
            throw new ValidationException("Invalid result for a distance set: " + this);
        }
        if (!atMostOneGameIsNonEnded()) {
            throw new ValidationException("All games must be completed with the exception of at most one");
        }
    }

    private boolean abnormalScore(long score1, long score2) {
        return ((score1 >= 7 || score2 >= 7) && Math.abs(score1 - score2) > 2);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
