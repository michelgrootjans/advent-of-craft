package people;

import static java.lang.System.lineSeparator;

import java.util.List;
import java.util.stream.Collectors;

public class TextPopulationPrinter implements PopulationPrinter {
    @Override
    public String print(List<Person> population) {
        return population.stream()
            .map(this::print)
            .collect(Collectors.joining(lineSeparator()));
    }

    private String print(Person person) {
        return printHeader(person) + printPets(person.pets());
    }

    private String printHeader(Person person) {
        return "%s %s".formatted(person.firstName(), person.lastName());
    }

    private String printPets(List<Pet> pets) {
        if(pets.isEmpty()) return "";
        return " who owns : %s".formatted(pets.stream().map(this::print).collect(Collectors.joining(" ")));
    }

    private String print(Pet pet) {
        return pet.name();
    }
}
