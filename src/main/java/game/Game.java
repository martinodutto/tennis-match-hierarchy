package game;

import interfaces.Abortable;

import java.util.Objects;

public class Game implements Abortable {

    private final GameScores score1;
    private final GameScores score2;

    private Game(GameScores score1, GameScores score2) {
        this.score1 = Objects.requireNonNull(score1, "Invalid null game score");
        this.score2 = Objects.requireNonNull(score2, "Invalid null game score");
    }

    public static Game ofIncompletedScore(IncompletedGameScores score1, IncompletedGameScores score2) {
        return new Game(score1, score2);
    }

    public static Game ofFirstPlayer() {
        return new Game(CompletedGameScores.WON, CompletedGameScores.LOST);
    }

    public static Game ofSecondPlayer() {
        return new Game(CompletedGameScores.LOST, CompletedGameScores.WON);
    }

    public GameScores getScore1() {
        return score1;
    }

    public GameScores getScore2() {
        return score2;
    }

    public boolean ended() {
        return score1 instanceof CompletedGameScores;
    }
}
