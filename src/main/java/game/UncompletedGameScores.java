package game;

/**
 * Represents the score of an uncompleted game, but <em>uniquely from the point of view of one player</em>.
 */
public enum UncompletedGameScores implements GameScores {
    LOVE,
    FIFTEEN,
    THIRTY,
    FORTY,
    ADVANTAGE
}
