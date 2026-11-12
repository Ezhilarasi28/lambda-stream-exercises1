

package lexicon.se;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class PersonProcessor {

    public static List<Person> findPeople(
            List<Person> people,
            Predicate<Person> rule) {

        return people.stream()
                .filter(rule)
                .toList();
    }

    public static void applyToMatching(
            List<Person> people,
            Predicate<Person> rule,
            Consumer<Person> action) {

        people.stream()
                .filter(rule)
                .forEach(action);
    }
}
