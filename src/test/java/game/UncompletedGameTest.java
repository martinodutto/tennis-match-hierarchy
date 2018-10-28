package game;

import exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static game.UncompletedGame.ofScore;
import static game.UncompletedGameScores.*;
import static org.junit.jupiter.api.Assertions.*;

class UncompletedGameTest {

    @Test
    void validatesAsExpected() {
        // invalid partial results
        assertThrows(ValidationException.class, () -> ofScore(ADVANTAGE, LOVE));
        assertThrows(ValidationException.class, () -> ofScore(FIFTEEN, ADVANTAGE));
        assertThrows(ValidationException.class, () -> ofScore(ADVANTAGE, ADVANTAGE));
        assertThrows(ValidationException.class, () -> ofScore(THIRTY, ADVANTAGE));
        assertThrows(ValidationException.class, () -> ofScore(LOVE, LOVE));
        // valid partial results
        assertDoesNotThrow(() -> ofScore(THIRTY, THIRTY));
        assertDoesNotThrow(() -> ofScore(ADVANTAGE, FORTY));
        assertDoesNotThrow(() -> ofScore(FORTY, ADVANTAGE));
        assertDoesNotThrow(() -> ofScore(FORTY, FORTY));
        assertDoesNotThrow(() -> ofScore(FIFTEEN, LOVE));
        assertDoesNotThrow(() -> ofScore(FIFTEEN, FORTY));
    }

    @Test
    void isNeverTerminated() throws ValidationException {
        assertFalse(ofScore(FIFTEEN, LOVE).terminated());
        assertFalse(ofScore(ADVANTAGE, FORTY).terminated());
        assertFalse(ofScore(THIRTY, FORTY).terminated());
        assertFalse(ofScore(LOVE, FORTY).terminated());
    }

    @Test
    void isNeverFlaggedAsWon() throws ValidationException {
        assertFalse(ofScore(FIFTEEN, FIFTEEN).wonByFirstPlayer());
        assertFalse(ofScore(FORTY, LOVE).wonByFirstPlayer());
        assertFalse(ofScore(LOVE, THIRTY).wonBySecondPlayer());
        assertFalse(ofScore(FORTY, FORTY).wonBySecondPlayer());
        assertFalse(ofScore(ADVANTAGE, FORTY).wonByFirstPlayer());
    }
}