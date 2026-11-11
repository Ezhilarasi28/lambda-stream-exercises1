package lexicon.se;
public class Person {

    private String name;
    private int age;
    private String city;
    private boolean active;

    public Person(String name, int age,
                  String city, boolean active) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.active = active;

    }
    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}


