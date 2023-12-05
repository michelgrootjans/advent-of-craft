package people;

import static java.lang.Integer.MAX_VALUE;

import java.util.ArrayList;
import java.util.List;

public record Person(String firstName, String lastName, List<Pet> pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, new ArrayList<>());
    }

    public Person addPet(PetType petType, String name, int age) {
        pets.add(new Pet(petType, name, age));
        return this;
    }

    public int youngestPetAge() {
        return pets().stream()
            .mapToInt(Pet::age)
            .min()
            .orElse(MAX_VALUE);
    }
}
