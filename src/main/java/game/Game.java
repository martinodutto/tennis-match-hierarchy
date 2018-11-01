package game;

import exceptions.ValidationException;
import interfaces.Terminable;
import interfaces.Validable;
import interfaces.Winnable;

import java.util.Objects;

public abstract class Game implements Terminable, Validable, Winnable {

    private final GameScores score1;
    private final GameScores score2;

    Game(GameScores score1, GameScores score2) throws ValidationException {
        this.score1 = Objects.requireNonNull(score1, "Invalid null game score");
        this.score2 = Objects.requireNonNull(score2, "Invalid null game score");
        validate();
    }

    public GameScores getScoreForFirstPlayer() {
        return score1;
    }

    public GameScores getScoreForSecondPlayer() {
        return score2;
    }

    @Override
    public String toString() {
        return score1 + " - " + score2;
    }
}
