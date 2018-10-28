package interfaces;

/**
 * Contract for matches, sets, games and, generically, classes for which "terminating" has a meaning.
 */
public interface Terminable {

    /**
     * Whenever the implementing class can be considered "terminated".
     *
     * @return True if the class has "terminated".
     */
    boolean terminated();
}
