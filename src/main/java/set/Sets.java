package set;

import exceptions.ValidationException;
import game.Game;
import game.IncompletedGameScores;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Sets {

    private Sets() {
        throw new AssertionError("Sets cannot be instantiated");
    }

    public static TieBreakSet tieBreakSet(int firstPlayerScore, int secondPlayerScore) throws ValidationException {
        if (firstPlayerScore < 0 || secondPlayerScore < 0) {
            throw new IllegalArgumentException("Invalid negative number of games won by a player");
        }
        return TieBreakSet.ofGames(setOfGamesFromScore(firstPlayerScore, secondPlayerScore));
    }

    public static TieBreakSet tieBreakSetWithIncompletedGame(int firstPlayerScore,
                                                             int secondPlayerScore,
                                                             IncompletedGameScores score1,
                                                             IncompletedGameScores score2) throws ValidationException {

        final Set<Game> games = setOfGamesFromScore(firstPlayerScore, secondPlayerScore);
        games.add(Game.ofIncompletedScore(score1, score2));

        return TieBreakSet.ofGames(games);
    }

    public static DistanceSet distanceSet(int firstPlayerScore, int secondPlayerScore) throws ValidationException {
        if (firstPlayerScore < 0 || secondPlayerScore < 0) {
            throw new IllegalArgumentException("Invalid negative number of games won by a player");
        }
        return DistanceSet.ofGames(setOfGamesFromScore(firstPlayerScore, secondPlayerScore));
    }

    public static DistanceSet distanceSetWithIncompletedGame(int firstPlayerScore,
                                                             int secondPlayerScore,
                                                             IncompletedGameScores score1,
                                                             IncompletedGameScores score2) throws ValidationException {

        final Set<Game> games = setOfGamesFromScore(firstPlayerScore, secondPlayerScore);
        games.add(Game.ofIncompletedScore(score1, score2));

        return DistanceSet.ofGames(games);
    }

    private static Set<Game> setOfGamesFromScore(int firstPlayerScore, int secondPlayerScore) {
        final var fpGames = IntStream.range(0, firstPlayerScore)
                .mapToObj(i -> Game.ofFirstPlayer())
                .collect(toList());
        final var spGames = IntStream.range(0, secondPlayerScore)
                .mapToObj(i -> Game.ofSecondPlayer())
                .collect(toList());

        return Stream.concat(fpGames.stream(), spGames.stream())
                .collect(toSet());
    }
}
