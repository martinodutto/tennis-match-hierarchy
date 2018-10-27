package set;

import game.CompletedGameScores;
import game.Game;
import interfaces.Durable;

import java.util.Collections;
import java.util.List;

public abstract class AbstractSet implements Durable {

    private final List<Game> games;

    protected AbstractSet(List<Game> games) {
        this.games = Collections.unmodifiableList(games);
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

    protected abstract boolean valid();
}
