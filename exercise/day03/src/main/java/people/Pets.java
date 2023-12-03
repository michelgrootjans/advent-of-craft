package people;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Pets {
    private final List<Pet> pets;

    public Pets() {
        this.pets = new ArrayList<>();
    }

    private Pets(List<Pet> pets, Pet pet) {
        this.pets = Stream.concat(pets.stream(), Stream.of(pet)).toList();
    }

    public Pets add(Pet pet) {
        return new Pets(pets, pet);
    }

    public int youngestPetAge() {
        return pets.stream().mapToInt(Pet::age).min().orElse(Integer.MAX_VALUE);
    }
}
