package lexicon.se;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );

        System.out.println("\n--------Active Adults:---------------");

        List<Person> activeAdults = PersonProcessor.findPeople(
                people,
                p -> p.isActive() && p.getAge() >= 18
        );

        activeAdults.forEach(System.out::println);

        System.out.println("\n------Inactive People:-----------");

        PersonProcessor.applyToMatching(
                people,
                p -> !p.isActive(),
                p -> System.out.println(p.getName())
        );



        System.out.println("\n-----------Active People:----------");

        List<Person> activePeople = PersonProcessor.findPeople(
                people, p -> p.isActive()
        );

        activePeople.forEach(System.out::println);

        System.out.println("\n----------Adults:------------------");

        PersonProcessor.applyToMatching(
                people,
                p -> p.getAge() >= 18,
                p -> System.out.println(p.getName())
        );


        Predicate<Person> isActive = p -> p.isActive();

        Predicate<Person> isAdult = p -> p.getAge() >= 18;

        Predicate<Person> livesInStockholm =
                p -> p.getCity().equalsIgnoreCase("Stockholm");

        System.out.println("\n--------Active Adults:---------");
        people.stream()
                .filter(isActive.and(isAdult))
                .forEach(System.out::println);

        System.out.println("\n--------------Adults or Stockholm:-------");
        people.stream()
                .filter(isAdult.or(livesInStockholm))
                .forEach(System.out::println);

        System.out.println("\n--------Inactive People:--------");
        people.stream()
                .filter(isActive.negate())
                .forEach(System.out::println);



// Rule 1: Check if person is active
        PersonRule activeRule = person -> person.isActive();

// Rule 2: Check if person is an adult
        PersonRule adultRule = person -> person.getAge() >= 18;

// Rule 3: Check if person lives in Stockholm
        PersonRule stockholmRule =
                person -> person.getCity().equals("Stockholm");

        List<Person> adults = filterPeople(people, adultRule);


        for (Person person : adults) {
            System.out.println(person.getName());
        }
        // Action 1: Print person's name
        PersonAction printName =
                person -> System.out.println(person.getName());
        printName.execute(people.get(0));

        // Action 2: Print email message
        PersonAction sendEmail =
                person -> System.out.println("Send email to " + person.getName());
        sendEmail.execute(people.get(0));

        // Part 2(stream API CODE) - Task 1: Filter active people
        System.out.println("\n-------------Active People:------------");

        people.stream()
                .filter(person -> person.isActive())
                .forEach(person -> System.out.println(person.getName()));
        // Part 2 (STEAM API) - Task 2: Map all names
        System.out.println("\n-------------All Names:-----------------");

        people.stream()
                .map(person -> person.getName())
                .forEach(name -> System.out.println(name));


        // Part 2(steam api) - Task 3: Count adults
        long adultCount = people.stream()
                .filter(person -> person.getAge() >= 18)
                .count();

        System.out.println("Total Adults: " + adultCount);


        // Part 2(steam api) - Task 4: Sort by age
        System.out.println("People sorted by age:");

        people.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .forEach(person ->
                        System.out.println(person.getName() + " - " + person.getAge()));


        // Part 2(steam api) - Task 5: First active person in Stockholm
        System.out.println("First active person in Stockholm:");

        people.stream()
                .filter(person -> person.isActive())
                .filter(person -> person.getCity().equals("Stockholm"))
                .findFirst()
                .ifPresent(person -> System.out.println(person.getName()));



        // Part 2 - Optional Task 6: Multiple Filters

        System.out.println("\n===== TASK 6: MULTIPLE FILTERS =====");

        // Active adults
        System.out.println("\nActive Adults:");
        people.stream()
                .filter(p -> p.isActive() && p.getAge() >= 18)
                .forEach(p -> System.out.println(p.getName()));

        // Active people in Stockholm
        System.out.println("\nActive People in Stockholm:");
        people.stream()
                .filter(p -> p.isActive()
                        && p.getCity().equalsIgnoreCase("Stockholm"))
                .forEach(p -> System.out.println(p.getName()));

        // Inactive people older than 30
        System.out.println("\nInactive People Older Than 30:");
        people.stream()
                .filter(p -> !p.isActive() && p.getAge() > 30)
                .forEach(p -> System.out.println(p.getName()));



        // Part 2 - Optional Task 7: Distinct and Sorting

        System.out.println("\n----------TASK 7: DISTINCT AND SORTING------");

        // 1. Get sorted unique cities
        List<String> uniqueCities = people.stream()
                .map(Person::getCity)
                .distinct()
                .sorted()
                .toList();

        System.out.println("\n---------Sorted Unique Cities:------------");
        uniqueCities.forEach(System.out::println);

        // 2. Get sorted unique first letters of names
        List<String> firstLetters = people.stream()
                .map(p -> p.getName().substring(0, 1))
                .distinct()
                .sorted()
                .toList();

        System.out.println("\n---------Sorted Unique First Letters:----------");
        firstLetters.forEach(System.out::println);



        // Part 2 - Optional Task 8: Mapping and Formatting

        System.out.println("\n---------TASK 8: MAPPING AND FORMATTING-----------");

        // 1. Format person details
        List<String> personDetails = people.stream()
                .map(p -> p.getName() + " (" + p.getAge()
                        + ") - " + p.getCity())
                .toList();

        System.out.println("\nPerson Details:");
        personDetails.forEach(System.out::println);

        // 2. Create email addresses
        List<String> emails = people.stream()
                .map(p -> p.getName().toLowerCase() + "@example.com")
                .toList();

        System.out.println("\nEmail Addresses:");
        emails.forEach(System.out::println);



    }

    public static List<Person> filterPeople(
            List<Person> people, PersonRule rule) {

        List<Person> result = new ArrayList<>();

        for (Person person : people) {
            if (rule.test(person)) {
                result.add(person);
            }
        }

        return result;
    }

}