package match;

import exceptions.ValidationException;
import game.TieBreakGame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static set.Sets.tieBreakSet;
import static set.Sets.tieBreakSetWithTieBreakGame;

class MatchTest {

    @Test
    void getScoreMethodsWorkAsExpected() throws ValidationException {
        final ThreeSetter tS1 = ThreeSetter.ofSets(List.of(
                tieBreakSet(5, 7),
                tieBreakSet(6, 0),
                tieBreakSetWithTieBreakGame(TieBreakGame.ofScore(4, 7))
        ));
        assertEquals(1, tS1.getScoreForFirstPlayer());
        assertEquals(2, tS1.getScoreForSecondPlayer());

        // unfinished
        final ThreeSetter tS2 = ThreeSetter.ofSets(List.of(
                tieBreakSet(4, 6),
                tieBreakSet(6, 0),
                tieBreakSet(6, 6)
        ));
        assertEquals(1, tS2.getScoreForFirstPlayer());
        assertEquals(1, tS2.getScoreForSecondPlayer());

        // TODO
    }
}