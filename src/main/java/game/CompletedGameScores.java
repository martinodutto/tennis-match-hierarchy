package game;

/**
 * Represents the outcome of a completed game, but <em>uniquely from the point of view of one player</em>.
 */
public enum CompletedGameScores implements GameScores {
    WON,
    LOST
}
