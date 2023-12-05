package people;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

import java.util.List;
import java.util.stream.Collectors;

public class TextPopulationPrinter implements PopulationPrinter {
    @Override
    public String print(List<Person> population) {
        return population.stream()
            .map(p -> print(p) + printPets(p.pets()))
            .collect(Collectors.joining(lineSeparator()));
    }

    private String print(Person person) {
        return format("%s %s", person.firstName(), person.lastName());
    }

    private String printPets(List<Pet> pets) {
        if(pets.isEmpty()) return "";
        return " who owns : " + String.join(" ", pets.stream().map(this::print).toList());
    }

    private String print(Pet pet) {
        return pet.name();
    }
}
