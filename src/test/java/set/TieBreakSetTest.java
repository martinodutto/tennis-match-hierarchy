package set;

import exceptions.ValidationException;
import game.CompletedGame;
import game.TieBreakGame;
import game.UncompletedGame;
import game.UncompletedGameScores;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static set.SetTest.setOfGamesFromScore;
import static set.TieBreakSet.ofGames;

class TieBreakSetTest {

    @Test
    void isCorrectlyLabeledTerminated() throws ValidationException {
        // unfinished sets
        assertFalse(ofGames(setOfGamesFromScore(5, 5)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(4, 3)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(6, 6)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(0, 2)).terminated());

        final TieBreakSet set1 = ofGames(java.util.Set.of(
                UncompletedGame.ofScore(UncompletedGameScores.THIRTY, UncompletedGameScores.FIFTEEN),
                CompletedGame.ofSecondPlayer(),
                CompletedGame.ofFirstPlayer(),
                CompletedGame.ofFirstPlayer()
        ));
        assertFalse(set1.terminated());

        // finished sets
        assertTrue(ofGames(setOfGamesFromScore(6, 2)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(4, 6)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(7, 6, true)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(6, 7, true)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(0, 6)).terminated());
    }

    @Test
    void isCorrectlyValidated() throws ValidationException {
        // invalid instances
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(8, 1)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(6, 8)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(0, 0)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(4, 7)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(9, 7)));
        assertThrows(ValidationException.class, () -> ofGames(java.util.Set.of(
                TieBreakGame.ofScore(16, 18),
                CompletedGame.ofSecondPlayer(),
                UncompletedGame.ofScore(UncompletedGameScores.ADVANTAGE, UncompletedGameScores.FORTY)
        )));
        assertThrows(ValidationException.class, () -> ofGames(java.util.Set.of(
                TieBreakGame.ofScore(16, 18),
                TieBreakGame.ofScore(7, 6),
                CompletedGame.ofFirstPlayer()
        )));
        assertThrows(ValidationException.class, () -> ofGames(java.util.Set.of(
                UncompletedGame.ofScore(UncompletedGameScores.THIRTY, UncompletedGameScores.FORTY),
                UncompletedGame.ofScore(UncompletedGameScores.FIFTEEN, UncompletedGameScores.FIFTEEN),
                CompletedGame.ofFirstPlayer()
        )));

        // these instances should validate
        ofGames(setOfGamesFromScore(6, 5));
        ofGames(setOfGamesFromScore(6, 7, true));
        ofGames(setOfGamesFromScore(2, 6));
        ofGames(setOfGamesFromScore(3, 0));
    }
}