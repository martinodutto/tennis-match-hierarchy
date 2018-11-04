package set;

import exceptions.ValidationException;
import game.*;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Sets {

    private Sets() {
        throw new AssertionError("Sets cannot be instantiated");
    }

    public static TieBreakSet tieBreakSet(int firstPlayerScore, int secondPlayerScore) throws ValidationException {
        if (firstPlayerScore < 0 || secondPlayerScore < 0) {
            throw new IllegalArgumentException("Invalid negative number of games won by a player");
        }
        if ((firstPlayerScore == 7 && secondPlayerScore == 6) || (firstPlayerScore == 6 && secondPlayerScore == 7)) {
            throw new UnsupportedOperationException("Wrong method called: use #tieBreakSetWithTieBreakGame instead");
        }
        return TieBreakSet.ofGames(setOfGamesFromScore(firstPlayerScore, secondPlayerScore));
    }

    public static TieBreakSet tieBreakSetWithUncompletedGame(int firstPlayerScore,
                                                             int secondPlayerScore,
                                                             UncompletedGame uncompletedGame) throws ValidationException {

        final Set<Game> games = setOfGamesFromScore(firstPlayerScore, secondPlayerScore);
        games.add(uncompletedGame);

        return TieBreakSet.ofGames(games);
    }

    public static TieBreakSet tieBreakSetWithTieBreakGame(TieBreakGame tieBreakGame) throws ValidationException {
        final Set<Game> games = setOfGamesFromScore(6, 6);
        games.add(tieBreakGame);

        return TieBreakSet.ofGames(games);
    }

    public static DistanceSet distanceSet(int firstPlayerScore, int secondPlayerScore) throws ValidationException {
        if (firstPlayerScore < 0 || secondPlayerScore < 0) {
            throw new IllegalArgumentException("Invalid negative number of games won by a player");
        }
        return DistanceSet.ofGames(setOfGamesFromScore(firstPlayerScore, secondPlayerScore));
    }

    public static DistanceSet distanceSetWithUncompletedGame(int firstPlayerScore,
                                                             int secondPlayerScore,
                                                             UncompletedGameScores score1,
                                                             UncompletedGameScores score2) throws ValidationException {

        final Set<Game> games = setOfGamesFromScore(firstPlayerScore, secondPlayerScore);
        games.add(UncompletedGame.ofScore(score1, score2));

        return DistanceSet.ofGames(games);
    }

    private static Set<Game> setOfGamesFromScore(int firstPlayerScore, int secondPlayerScore) {
        final var fpGames = IntStream.range(0, firstPlayerScore)
                .mapToObj(i -> CompletedGame.ofFirstPlayer())
                .collect(toSet());
        final var spGames = IntStream.range(0, secondPlayerScore)
                .mapToObj(i -> CompletedGame.ofSecondPlayer())
                .collect(toSet());

        return Stream.concat(fpGames.stream(), spGames.stream())
                .collect(toSet());
    }
}
