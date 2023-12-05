package people;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

import java.util.List;

public class TextPopulationPrinter implements PopulationPrinter {
    @Override
    public String print(List<Person> population) {
        final var response = new StringBuilder();

        for (var person : population) {
            response.append(format("%s %s", person.firstName(), person.lastName()));

            if (!person.pets().isEmpty()) {
                response.append(" who owns : ");
            }

            for (var pet : person.pets()) {
                response.append(pet.name()).append(" ");
            }

            if (!population.getLast().equals(person)) {
                response.append(lineSeparator());
            }
        }
        return response.toString();
    }
}
