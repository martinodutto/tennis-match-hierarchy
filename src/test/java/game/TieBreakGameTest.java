package game;

import exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static game.TieBreakGame.ofScore;
import static org.junit.jupiter.api.Assertions.*;

class TieBreakGameTest {

    @Test
    void validatesAsExpected() {
        // various impossible results
        assertThrows(ValidationException.class, () -> ofScore(1, 8));
        assertThrows(ValidationException.class, () -> ofScore(17, 6));
        assertThrows(ValidationException.class, () -> ofScore(5, 8));
        assertThrows(ValidationException.class, () -> ofScore(9, 4));
        assertThrows(ValidationException.class, () -> ofScore(8, 4));
        assertThrows(ValidationException.class, () -> ofScore(173, 170));

        // negative outcomes
        assertThrows(IllegalArgumentException.class, () -> ofScore(-1, 3));

        // "empty" game
        assertThrows(ValidationException.class, () -> ofScore(0, 0));

        // valid results
        assertDoesNotThrow(() -> ofScore(7, 6));
        assertDoesNotThrow(() -> ofScore(5, 7));
        assertDoesNotThrow(() -> ofScore(6, 6));
        assertDoesNotThrow(() -> ofScore(1, 4));
        assertDoesNotThrow(() -> ofScore(282, 281));
        assertDoesNotThrow(() -> ofScore(36, 38));
    }

    @Test
    void isCorrectlyFlaggedTerminated() throws ValidationException {
        assertTrue(ofScore(7, 1).terminated());
        assertTrue(ofScore(4, 7).terminated());
        assertTrue(ofScore(7, 5).terminated());
        assertTrue(ofScore(5, 7).terminated());
        assertTrue(ofScore(9, 7).terminated());
        assertTrue(ofScore(122, 124).terminated());
        assertTrue(ofScore(0, 7).terminated());
        assertTrue(ofScore(2, 7).terminated());
        assertTrue(ofScore(8, 6).terminated());
        assertTrue(ofScore(6, 8).terminated());

        assertFalse(ofScore(4, 6).terminated());
        assertFalse(ofScore(10, 11).terminated());
        assertFalse(ofScore(6, 6).terminated());
        assertFalse(ofScore(145, 144).terminated());
        assertFalse(ofScore(7, 7).terminated());
        assertFalse(ofScore(8, 8).terminated());
    }

    @Test
    void isCorrectlyFlaggedAsWon() throws ValidationException {
        // first player point of view
        assertTrue(ofScore(7, 5).wonByFirstPlayer());
        assertFalse(ofScore(7, 5).wonBySecondPlayer());
        assertTrue(ofScore(7, 3).wonByFirstPlayer());
        assertFalse(ofScore(7, 3).wonBySecondPlayer());
        assertTrue(ofScore(7, 0).wonByFirstPlayer());
        assertFalse(ofScore(7, 0).wonBySecondPlayer());
        assertTrue(ofScore(12, 10).wonByFirstPlayer());
        assertFalse(ofScore(12, 10).wonBySecondPlayer());
        assertTrue(ofScore(277, 275).wonByFirstPlayer());
        assertFalse(ofScore(277, 275).wonBySecondPlayer());
        assertTrue(ofScore(7, 1).wonByFirstPlayer());
        assertFalse(ofScore(7, 1).wonBySecondPlayer());
        assertTrue(ofScore(8, 6).wonByFirstPlayer());
        assertFalse(ofScore(8, 6).wonBySecondPlayer());
        assertTrue(ofScore(9, 7).wonByFirstPlayer());
        assertFalse(ofScore(9, 7).wonBySecondPlayer());
        assertTrue(ofScore(21, 19).wonByFirstPlayer());
        assertFalse(ofScore(21, 19).wonBySecondPlayer());

        // second player point of view
        assertTrue(ofScore(5, 7).wonBySecondPlayer());
        assertFalse(ofScore(5, 7).wonByFirstPlayer());
        assertTrue(ofScore(1, 7).wonBySecondPlayer());
        assertFalse(ofScore(1, 7).wonByFirstPlayer());
        assertTrue(ofScore(2, 7).wonBySecondPlayer());
        assertFalse(ofScore(2, 7).wonByFirstPlayer());
        assertTrue(ofScore(11, 13).wonBySecondPlayer());
        assertFalse(ofScore(11, 13).wonByFirstPlayer());
        assertTrue(ofScore(26, 28).wonBySecondPlayer());
        assertFalse(ofScore(26, 28).wonByFirstPlayer());
        assertTrue(ofScore(6, 8).wonBySecondPlayer());
        assertFalse(ofScore(6, 8).wonByFirstPlayer());
        assertTrue(ofScore(7, 9).wonBySecondPlayer());
        assertFalse(ofScore(7, 9).wonByFirstPlayer());

        // for uncompleted tie-breaks, it does not make sense to test for winners
        assertThrows(IllegalStateException.class, () -> ofScore(6, 7).wonByFirstPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(6, 7).wonBySecondPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(5, 4).wonBySecondPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(5, 4).wonByFirstPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(18, 19).wonByFirstPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(1, 1).wonBySecondPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(3, 6).wonByFirstPlayer());
        assertThrows(IllegalStateException.class, () -> ofScore(8, 7).wonBySecondPlayer());
    }
}