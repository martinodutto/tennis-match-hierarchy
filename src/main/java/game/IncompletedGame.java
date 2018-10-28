package game;

import exceptions.ValidationException;

import static game.IncompletedGameScores.*;

public class IncompletedGame extends Game {

    private IncompletedGame(IncompletedGameScores scores1, IncompletedGameScores scores2) throws ValidationException {
        super(scores1, scores2);
    }

    public static IncompletedGame ofScore(IncompletedGameScores score1, IncompletedGameScores score2) throws ValidationException {
        return new IncompletedGame(score1, score2);
    }

    @Override
    public void validate() throws ValidationException {
        var score1 = getScore1();
        var score2 = getScore2();
        if (score1 == LOVE && score2 == LOVE) {
            throw new ValidationException("Empty game is not allowed");
        }
        if ((score1 == ADVANTAGE && score2 != FORTY) || (score1 != FORTY && score2 == ADVANTAGE)) {
            throw new ValidationException("Games ending in advantage for a player imply that the other is at forty");
        }
    }

    @Override
    public boolean ended() {
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
