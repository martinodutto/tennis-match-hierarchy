package set;

import exceptions.ValidationException;
import game.CompletedGame;
import game.UncompletedGame;
import game.UncompletedGameScores;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static set.DistanceSet.ofGames;
import static set.SetTest.setOfGamesFromScore;

class DistanceSetTest {

    @Test
    void isCorrectlyLabeledTerminated() throws ValidationException {
        // unfinished sets
        assertFalse(ofGames(setOfGamesFromScore(4, 5)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(3, 1)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(8, 8)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(12, 11)).terminated());
        assertFalse(ofGames(setOfGamesFromScore(7, 6)).terminated());

        final DistanceSet set1 = ofGames(java.util.Set.of(
                UncompletedGame.ofScore(UncompletedGameScores.THIRTY, UncompletedGameScores.FIFTEEN),
                CompletedGame.ofSecondPlayer(),
                CompletedGame.ofFirstPlayer(),
                CompletedGame.ofFirstPlayer()
        ));
        assertFalse(set1.terminated());

        // finished sets
        assertTrue(ofGames(setOfGamesFromScore(6, 2)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(10, 12)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(26, 24)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(6, 4)).terminated());
        assertTrue(ofGames(setOfGamesFromScore(5, 7)).terminated());
    }

    @Test
    void isCorrectlyValidated() throws ValidationException {
        // invalid instances
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(18, 1)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(70, 73)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(0, 0)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(7, 4)));
        assertThrows(ValidationException.class, () -> ofGames(setOfGamesFromScore(9, 6)));
        assertThrows(ValidationException.class, () -> ofGames(java.util.Set.of(
                UncompletedGame.ofScore(UncompletedGameScores.FORTY, UncompletedGameScores.FORTY),
                UncompletedGame.ofScore(UncompletedGameScores.FIFTEEN, UncompletedGameScores.FORTY),
                CompletedGame.ofFirstPlayer()
        )));

        // these instances should validate
        ofGames(setOfGamesFromScore(21, 20));
        ofGames(setOfGamesFromScore(6, 7));
        ofGames(setOfGamesFromScore(5, 6));
        ofGames(setOfGamesFromScore(3, 5));
    }
}