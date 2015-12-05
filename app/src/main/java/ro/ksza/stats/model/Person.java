package ro.ksza.stats.model;

public class Person {

    private long id;
    private final String name;
    private final int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public Person(final String name, final int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[Name: " + name + ", Age: " + age + "]";
    }
}
