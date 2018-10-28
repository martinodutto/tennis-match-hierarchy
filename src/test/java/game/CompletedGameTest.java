package game;

import org.junit.jupiter.api.Test;

import static game.CompletedGame.ofFirstPlayer;
import static game.CompletedGame.ofSecondPlayer;
import static org.junit.jupiter.api.Assertions.*;

class CompletedGameTest {

    @Test
    void alwaysValidates() {
        assertDoesNotThrow(CompletedGame::ofFirstPlayer);
        assertDoesNotThrow(CompletedGame::ofSecondPlayer);
    }

    @Test
    void isAlwaysTerminated() {
        assertTrue(ofFirstPlayer().terminated());
        assertTrue(ofSecondPlayer().terminated());
    }

    @Test
    void isCorrectlyFlaggedAsWon() {
        assertTrue(ofFirstPlayer().wonByFirstPlayer());
        assertFalse(ofFirstPlayer().wonBySecondPlayer());
        assertTrue(ofSecondPlayer().wonBySecondPlayer());
        assertFalse(ofSecondPlayer().wonByFirstPlayer());
    }
}