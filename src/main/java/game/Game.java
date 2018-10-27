package game;

import interfaces.Durable;

public class Game implements Durable {

    private final GameScores score1;
    private final GameScores score2;

    private Game(GameScores score1, GameScores score2) {
        this.score1 = score1;
        this.score2 = score2;
    }

    public static Game ofIncompletedScore(IncompletedGameScores score1, IncompletedGameScores score2) {
        return new Game(score1, score2);
    }

    public static Game wonByFirstPlayer() {
        return new Game(CompletedGameScores.WON, CompletedGameScores.LOST);
    }

    public static Game wonBySecondPlayer() {
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
