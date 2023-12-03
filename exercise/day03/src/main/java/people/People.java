package people;

import static java.util.Comparator.comparingInt;

import java.util.List;
import java.util.Optional;

public class People {
    private final List<Person> population;

    public People(Person... population) {
        this.population = List.of(population);
    }

    public Optional<Person> youngestPetOwner() {
        return population.stream()
            .min(comparingInt(Person::ageOfYoungestPet));
    }
}
