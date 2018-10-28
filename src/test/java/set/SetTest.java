package set;

import exceptions.ValidationException;
import game.Game;
import game.IncompletedGameScores;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    @Test
    void getScoreMethodsWorkAsExpected() throws ValidationException {
        final var games1 = java.util.Set.of(
                Game.ofFirstPlayer(),
                Game.ofSecondPlayer(),
                Game.ofFirstPlayer(),
                Game.ofFirstPlayer(),
                Game.ofIncompletedScore(IncompletedGameScores.FIFTEEN, IncompletedGameScores.FORTY));

        final Set set1 = TieBreakSet.ofGames(games1);
        assertEquals(3, set1.getScoreForFirstPlayer());
        assertEquals(1, set1.getScoreForSecondPlayer());

        final Set set2 = TieBreakSet.ofGames(setOfGamesFromScore(3, 6));
        assertEquals(3, set2.getScoreForFirstPlayer());
        assertEquals(6, set2.getScoreForSecondPlayer());

        final var games3 = setOfGamesFromScore(4, 0);
        games3.add(Game.ofIncompletedScore(IncompletedGameScores.THIRTY, IncompletedGameScores.LOVE));

        final Set set3 = DistanceSet.ofGames(games3);
        assertEquals(4, set3.getScoreForFirstPlayer());
        assertEquals(0, set3.getScoreForSecondPlayer());
    }

    @Test
    void wonByMethodsWorkAsExpected() throws ValidationException {
        final DistanceSet set1 = DistanceSet.ofGames(setOfGamesFromScore(8, 6));
        assertTrue(set1.wonByFirstPlayer());
        assertFalse(set1.wonBySecondPlayer());

        final DistanceSet set2 = DistanceSet.ofGames(setOfGamesFromScore(13, 15));
        assertTrue(set2.wonBySecondPlayer());
        assertFalse(set2.wonByFirstPlayer());

        final TieBreakSet set3 = TieBreakSet.ofGames(setOfGamesFromScore(3, 5));
        assertThrows(IllegalStateException.class, set3::wonByFirstPlayer, "A 3-5 tiebreak set is not terminated");

        final DistanceSet set4 = DistanceSet.ofGames(setOfGamesFromScore(6, 7));
        assertThrows(IllegalStateException.class, set4::wonByFirstPlayer,"A 6-7 distance set is not terminated");

        final TieBreakSet set5 = TieBreakSet.ofGames(setOfGamesFromScore(7, 6));
        assertTrue(set5.wonByFirstPlayer());
        assertFalse(set5.wonBySecondPlayer());

        final TieBreakSet set6 = TieBreakSet.ofGames(setOfGamesFromScore(6, 4));
        assertTrue(set6.wonByFirstPlayer());
        assertFalse(set6.wonBySecondPlayer());

        final TieBreakSet set7 = TieBreakSet.ofGames(setOfGamesFromScore(6, 3));
        assertTrue(set7.wonByFirstPlayer());
        assertFalse(set7.wonBySecondPlayer());

        final TieBreakSet set8 = TieBreakSet.ofGames(setOfGamesFromScore(6, 2));
        assertTrue(set8.wonByFirstPlayer());
        assertFalse(set8.wonBySecondPlayer());

        final TieBreakSet set9 = TieBreakSet.ofGames(setOfGamesFromScore(6, 1));
        assertTrue(set9.wonByFirstPlayer());
        assertFalse(set9.wonBySecondPlayer());

        final TieBreakSet set10 = TieBreakSet.ofGames(setOfGamesFromScore(6, 0));
        assertTrue(set10.wonByFirstPlayer());
        assertFalse(set10.wonBySecondPlayer());

        final TieBreakSet set11 = TieBreakSet.ofGames(setOfGamesFromScore(7, 5));
        assertTrue(set11.wonByFirstPlayer());
        assertFalse(set11.wonBySecondPlayer());

        final TieBreakSet set12 = TieBreakSet.ofGames(setOfGamesFromScore(4, 6));
        assertTrue(set12.wonBySecondPlayer());
        assertFalse(set12.wonByFirstPlayer());

        final TieBreakSet set13 = TieBreakSet.ofGames(setOfGamesFromScore(3, 6));
        assertTrue(set13.wonBySecondPlayer());
        assertFalse(set13.wonByFirstPlayer());

        final TieBreakSet set14 = TieBreakSet.ofGames(setOfGamesFromScore(2, 6));
        assertTrue(set14.wonBySecondPlayer());
        assertFalse(set14.wonByFirstPlayer());

        final TieBreakSet set15 = TieBreakSet.ofGames(setOfGamesFromScore(1, 6));
        assertTrue(set15.wonBySecondPlayer());
        assertFalse(set15.wonByFirstPlayer());

        final TieBreakSet set16 = TieBreakSet.ofGames(setOfGamesFromScore(0, 6));
        assertTrue(set16.wonBySecondPlayer());
        assertFalse(set16.wonByFirstPlayer());

        final TieBreakSet set17 = TieBreakSet.ofGames(setOfGamesFromScore(5, 7));
        assertTrue(set17.wonBySecondPlayer());
        assertFalse(set17.wonByFirstPlayer());

        final DistanceSet set18 = DistanceSet.ofGames(setOfGamesFromScore(7, 5));
        assertTrue(set18.wonByFirstPlayer());
        assertFalse(set18.wonBySecondPlayer());

        final DistanceSet set19 = DistanceSet.ofGames(setOfGamesFromScore(5, 7));
        assertTrue(set19.wonBySecondPlayer());
        assertFalse(set19.wonByFirstPlayer());

        final DistanceSet set20 = DistanceSet.ofGames(setOfGamesFromScore(6, 3));
        assertTrue(set20.wonByFirstPlayer());
        assertFalse(set20.wonBySecondPlayer());

        final DistanceSet set21 = DistanceSet.ofGames(setOfGamesFromScore(6, 0));
        assertTrue(set21.wonByFirstPlayer());
        assertFalse(set21.wonBySecondPlayer());

        final DistanceSet set22 = DistanceSet.ofGames(setOfGamesFromScore(2, 6));
        assertTrue(set22.wonBySecondPlayer());
        assertFalse(set22.wonByFirstPlayer());

        final DistanceSet set23 = DistanceSet.ofGames(setOfGamesFromScore(4, 6));
        assertTrue(set23.wonBySecondPlayer());
        assertFalse(set23.wonByFirstPlayer());

        final DistanceSet set24 = DistanceSet.ofGames(setOfGamesFromScore(22, 20));
        assertTrue(set24.wonByFirstPlayer());
        assertFalse(set24.wonBySecondPlayer());

        final DistanceSet set25 = DistanceSet.ofGames(setOfGamesFromScore(12, 11));
        assertThrows(IllegalStateException.class, set25::wonByFirstPlayer, "A 12-11 distance set is not terminated");
    }

    private static java.util.Set<Game> setOfGamesFromScore(int firstPlayerScore, int secondPlayerScore) {
        final var fpGames = IntStream.range(0, firstPlayerScore)
                .mapToObj(i -> Game.ofFirstPlayer())
                .collect(toSet());
        final var spGames = IntStream.range(0, secondPlayerScore)
                .mapToObj(i -> Game.ofSecondPlayer())
                .collect(toSet());

        return Stream.concat(fpGames.stream(), spGames.stream())
                .collect(toSet());
    }
}