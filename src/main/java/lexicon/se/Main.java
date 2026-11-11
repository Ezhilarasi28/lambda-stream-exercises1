package lexicon.se;
import java.util.List;
import java.util.ArrayList;
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

        // Rule 1: Check if person is active
        PersonRule isActive = person -> person.isActive();

        // Rule 2: Check if person is an adult
        PersonRule isAdult = person -> person.getAge() >= 18;

        // Rule 3: Check if person lives in Stockholm
        PersonRule livesInStockholm =
                person -> person.getCity().equals("Stockholm");
        List<Person> adults = filterPeople(people, isAdult);

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
        System.out.println("Active People:");

        people.stream()
                .filter(person -> person.isActive())
                .forEach(person -> System.out.println(person.getName()));
        // Part 2 (STEAM API) - Task 2: Map all names
        System.out.println("All Names:");

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