package set;

import exceptions.ValidationException;
import game.Game;
import game.TieBreakGame;
import game.UncompletedGame;

public class TieBreakSet extends Set {

    private TieBreakSet(java.util.Set<Game> games) throws ValidationException {
        super(games);
    }

    static TieBreakSet ofGames(java.util.Set<Game> games) throws ValidationException {
        return new TieBreakSet(games);
    }

    @Override
    public boolean terminated() {
        long score1 = getScoreForFirstPlayer();
        long score2 = getScoreForSecondPlayer();

        return allGamesTerminated() &&
                (endedNormally(score1, score2) || endedWithTieBreak(score1, score2));
    }

    private boolean endedNormally(long score1, long score2) {
        return (score1 == 7 && score2 == 5) || (score1 == 5 && score2 == 7) ||
                (score1 == 6 && score2 <= 4) || (score1 <= 4 && score2 == 6);
    }

    private boolean endedWithTieBreak(long score1, long score2) {
        return (score1 == 7 && score2 == 6) || (score1 == 6 && score2 == 7);
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        java.util.Set<Game> games = getGames();
        if (games.size() == 0 || games.size() > 13) {
            throw new ValidationException("Invalid number of total games for a tie-break set: " + games.size());
        }
        long score1 = getScoreForFirstPlayer();
        long score2 = getScoreForSecondPlayer();
        if (score1 > 7 || score2 > 7) {
            throw new ValidationException("No player can win more than 7 games per tie-break set");
        }
        if (abnormalScore(score1, score2)) {
            throw new ValidationException("Invalid result for a set: " + this);
        }
        if (!atMostOneGameIsATieBreakXorIsUncompleted()) {
            throw new ValidationException("At most one tie-break XOR one uncompleted game is allowed per set");
        }
    }

    private boolean abnormalScore(long score1, long score2) {
        return (score1 == 7 && score2 < 5) || (score1 < 5 && score2 == 7);
    }

    private boolean atMostOneGameIsATieBreakXorIsUncompleted() {
        long tiebreaks = getGames()
                .stream()
                .filter(g -> g instanceof TieBreakGame)
                .count();
        long uncompletedGames = getGames()
                .stream()
                .filter(g -> g instanceof UncompletedGame)
                .count();

        return tiebreaks + uncompletedGames <= 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
