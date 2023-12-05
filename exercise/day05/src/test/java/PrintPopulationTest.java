import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import people.Person;
import people.PetType;
import people.TextPopulationPrinter;

import java.util.Arrays;
import java.util.List;

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
        assertThat(new TextPopulationPrinter().print(population))
            .isEqualTo(""
                + "Glenn Quagmire" + lineSeparator()
                + "Peter Griffin who owns : Tabby " + lineSeparator()
                + "Stewie Griffin who owns : Dolly Brian "
            );
    }
}
