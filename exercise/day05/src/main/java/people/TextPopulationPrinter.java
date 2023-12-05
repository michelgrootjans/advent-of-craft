package people;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

import java.util.List;

public class TextPopulationPrinter implements PopulationPrinter {
    @Override
    public String print(List<Person> population) {
        final var response = new StringBuilder();

        for (var person : population) {
            response.append(print(person) + printPets(person.pets()));

            for (var pet : person.pets()) {
                response.append(pet.name()).append(" ");
            }

            if (!population.getLast().equals(person)) {
                response.append(lineSeparator());
            }
        }
        return response.toString();
    }

    private String print(Person person) {
        return format("%s %s", person.firstName(), person.lastName());
    }

    private String printPets(List<Pet> pets) {
        if(pets.isEmpty()) return "";
        return " who owns : " + String.join("", pets.stream().map(this::print).toList());
    }

    private String print(Pet pet) {
        return "";
    }
}
