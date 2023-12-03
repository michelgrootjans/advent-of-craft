package people;

import static java.util.Comparator.comparingInt;

import java.util.List;
import java.util.Optional;

public class Pets {
    private final List<Pet> pets;

    public Pets(Pet... pets) {
        this.pets = List.of(pets);
    }

    public Optional<Pet> youngest() {
        return pets.stream()
            .min(comparingInt(Pet::age));
    }
}
