package people;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Pets {
    private final List<Pet> pets;

    public Pets() {
        this(new ArrayList<>());
    }

    private Pets(List<Pet> pets) {
        this.pets = pets;
    }

    public Pets add(Pet pet) {
        return new Pets(Stream.concat(pets.stream(), Stream.of(pet)).toList());
    }

    public int youngestPetAge() {
        return pets.stream().mapToInt(Pet::age).min().orElse(Integer.MAX_VALUE);
    }
}
