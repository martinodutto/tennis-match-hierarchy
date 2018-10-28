package match;

import exceptions.ValidationException;
import set.DistanceSet;
import set.Set;
import set.TieBreakSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Matches {

    private Matches() {
        throw new AssertionError("Matches cannot be instantiated");
    }

    public static ThreeSetter threeSetter(TieBreakSet set1, TieBreakSet set2, TieBreakSet set3) throws ValidationException {
        // List.of cannot be employed here, since elements could be null
        List<TieBreakSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);

        return threeSetter(sets);
    }

    public static ThreeSetter threeSetter(List<TieBreakSet> sets) throws ValidationException {
        Objects.requireNonNull(sets, "The list of sets must be not null");
        if (sets.isEmpty()) {
            throw new IllegalArgumentException("At least a set must be present");
        }
        // null sets are removed
        return ThreeSetter.ofSets(sets
                .stream()
                .filter(Objects::nonNull)
                .collect(toList()));

    }

    public static FiveSetter fiveSetterWithTieBreak(TieBreakSet set1,
                                                    TieBreakSet set2,
                                                    TieBreakSet set3,
                                                    TieBreakSet set4,
                                                    TieBreakSet set5) throws ValidationException {
        // List.of cannot be employed here, since elements could be null
        List<TieBreakSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        sets.add(set4);
        sets.add(set5);

        return fiveSetterWithTieBreak(sets);
    }

    public static FiveSetter fiveSetterWithTieBreak(List<TieBreakSet> sets) throws ValidationException {
        return fiveSetter(sets);
    }

    public static FiveSetter fiveSetterWithDistanceSet(TieBreakSet set1,
                                                       TieBreakSet set2,
                                                       TieBreakSet set3,
                                                       TieBreakSet set4,
                                                       DistanceSet set5) throws ValidationException {
        // List.of cannot be employed here, since elements could be null
        List<TieBreakSet> tbSets = new ArrayList<>();
        tbSets.add(set1);
        tbSets.add(set2);
        tbSets.add(set3);
        tbSets.add(set4);

        return fiveSetterWithDistanceSet(tbSets, set5);
    }

    public static FiveSetter fiveSetterWithDistanceSet(List<TieBreakSet> firstSets, DistanceSet lastSet) throws ValidationException {
        List<Set> l = new ArrayList<>(firstSets);
        l.add(lastSet);

        return fiveSetter(l);
    }

    // this method must stay private to ensure types are respected for the sets in the provided list
    // please use the other public methods instead
    private static FiveSetter fiveSetter(List<? extends Set> sets) throws ValidationException {
        Objects.requireNonNull(sets, "The list of sets must be not null");
        if (sets.isEmpty()) {
            throw new IllegalArgumentException("At least a set must be present");
        }
        return FiveSetter.ofSets(sets
                .stream()
                .filter(Objects::nonNull)
                .collect(toList()));
    }
}
