package game;

import exceptions.ValidationException;

public class CompletedGame extends Game {

    private CompletedGame(CompletedGameScores scores1, CompletedGameScores scores2) throws ValidationException {
        super(scores1, scores2);
    }

    public static CompletedGame ofFirstPlayer() {
        try {
            return new CompletedGame(CompletedGameScores.WON, CompletedGameScores.LOST);
        } catch (ValidationException e) {
            throw new AssertionError("A completed game should never fail to validate");
        }
    }

    public static CompletedGame ofSecondPlayer() {
        try {
            return new CompletedGame(CompletedGameScores.LOST, CompletedGameScores.WON);
        } catch (ValidationException e) {
            throw new AssertionError("A completed game should never fail to validate");
        }
    }

    @Override
    public void validate() throws ValidationException {
        // nothing to do
    }

    @Override
    public boolean terminated() {
        // by the very definition of the class
        return true;
    }

    @Override
    public boolean wonByFirstPlayer() {
        return getScoreForFirstPlayer() == CompletedGameScores.WON;
    }

    @Override
    public boolean wonBySecondPlayer() {
        return getScoreForSecondPlayer() == CompletedGameScores.WON;
    }
}
