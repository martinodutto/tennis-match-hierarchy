package game;

import exceptions.ValidationException;

import static game.UncompletedGameScores.*;

public class UncompletedGame extends Game {

    private UncompletedGame(UncompletedGameScores scores1, UncompletedGameScores scores2) throws ValidationException {
        super(scores1, scores2);
    }

    public static UncompletedGame ofScore(UncompletedGameScores score1, UncompletedGameScores score2) throws ValidationException {
        return new UncompletedGame(score1, score2);
    }

    @Override
    public void validate() throws ValidationException {
        var score1 = getScoreForFirstPlayer();
        var score2 = getScoreForSecondPlayer();
        if (score1 == LOVE && score2 == LOVE) {
            throw new ValidationException("Empty game is not allowed");
        }
        if ((score1 == ADVANTAGE && score2 != FORTY) || (score1 != FORTY && score2 == ADVANTAGE)) {
            throw new ValidationException("Games ending in advantage for a player imply that the other is at forty");
        }
    }

    @Override
    public boolean terminated() {
        // by the very definition of the class
        return false;
    }

    @Override
    public boolean wonByFirstPlayer() {
        return false;
    }

    @Override
    public boolean wonBySecondPlayer() {
        return false;
    }
}
