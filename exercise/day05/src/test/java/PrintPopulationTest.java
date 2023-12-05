import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import people.Person;
import people.PetType;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;

class PrintPopulationTest {
    private static List<Person> population;

    @BeforeAll
    static void setup() {
        population = Arrays.asList(
                new Person("Glenn", "Quagmire"),
                new Person("Peter", "Griffin")
                        .addPet(PetType.CAT, "Tabby", 2),
                new Person("Stewie", "Griffin")
                        .addPet(PetType.CAT, "Dolly", 3)
                        .addPet(PetType.DOG, "Brian", 9)
        );
    }

    @Test
    void peopleWithTheirPets() {
        assertThat(printPopulation2(population))
            .isEqualTo(""
                + "Glenn Quagmire" + lineSeparator()
                + "Peter Griffin who owns : Tabby " + lineSeparator()
                + "Stewie Griffin who owns : Dolly Brian "
            );
    }

    private String printPopulation2(List<Person> population) {
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
