package game;

import exceptions.ValidationException;

public class TieBreakGame extends Game {

    private final long s1;
    private final long s2;

    private TieBreakGame(TieBreakGameScores scores1, TieBreakGameScores scores2) throws ValidationException {
        super(scores1, scores2);
        this.s1 = Long.valueOf(getScore1().toString());
        this.s2 = Long.valueOf(getScore2().toString());
    }

    public static TieBreakGame ofScore(long score1, long score2) throws ValidationException {
        return new TieBreakGame(TieBreakGameScores.of(score1), TieBreakGameScores.of(score2));
    }

    @Override
    public void validate() throws ValidationException {
        long s1 = Long.valueOf(getScore1().toString());
        long s2 = Long.valueOf(getScore2().toString());
        if (s1 < 0 || s2 < 0) {
            throw new ValidationException("Points in the tie-break must be positive");
        }
        if (s1 == 0 && s2 == 0) {
            throw new ValidationException("A tie-break cannot be empty (0 - 0)");
        }
        if ((s1 > 7 && s2 <= 5) || (s1 <= 5 && s2 > 7) || (s1 >= 6 && s2 >= 6 && Math.abs(s1 - s2) > 2)) {
            throw new ValidationException("Invalid result for a tie-break: " + this);
        }
    }

    @Override
    public boolean terminated() {
        return (s1 == 7 && s2 <= 5) || (s1 <= 5 && s2 == 7) ||
                (s1 >= 6 && s2 >= 6 && Math.abs(s1 - s2) == 2);
    }

    @Override
    public boolean wonByFirstPlayer() {
        if (terminated()) {
            return s1 == 7 && s2 <= 5 || (s1 >= 6 && s2 >= 6 && s1 - s2 == 2);
        } else {
            throw new IllegalStateException("Unterminated game. A winner can't be determined");
        }
    }

    @Override
    public boolean wonBySecondPlayer() {
        if (terminated()) {
            return s2 == 7 && s1 <= 5 || (s1 >= 6 && s2 >= 6 && s2 - s1 == 2);
        } else {
            throw new IllegalStateException("Unterminated game. A winner can't be determined");
        }
    }
}
