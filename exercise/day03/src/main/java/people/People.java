package people;

import java.util.Comparator;
import java.util.List;

public class People {
    private final List<Person> population;

    public People(List<Person> population) {

        this.population = population;
    }

    public Person youngestPetOwner() {
        return population.stream().min(Comparator.comparingInt(person -> person.pets().stream().mapToInt(Pet::age).min().orElse(Integer.MAX_VALUE)))
            .orElse(null);
    }
}
