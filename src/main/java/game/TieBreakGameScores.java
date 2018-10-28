package game;

public class TieBreakGameScores implements GameScores {

    private long score;

    private TieBreakGameScores(long score) {
        this.score = score;
    }

    public static TieBreakGameScores of(long score) {
        if (score < 0) {
            throw new IllegalArgumentException("Invalid negative tie-break score: " + score);
        }
        return new TieBreakGameScores(score);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
