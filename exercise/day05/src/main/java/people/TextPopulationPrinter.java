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
        return person.fullName() + printPets(person);
    }

    private String printPets(Person person) {
        if(person.pets().isEmpty()) return "";
        return " who owns : %s".formatted(petNames(person));
    }

    private String petNames(Person person) {
        return person.pets().stream()
            .map(Pet::name)
            .collect(Collectors.joining(" "));
    }
}
