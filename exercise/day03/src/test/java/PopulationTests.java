import static org.assertj.core.api.Assertions.assertThat;
import static people.PetType.BIRD;
import static people.PetType.CAT;
import static people.PetType.DOG;
import static people.PetType.HAMSTER;
import static people.PetType.SNAKE;
import static people.PetType.TURTLE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import people.People;
import people.Person;
import people.Pet;

class PopulationTests {
    @Test
    void no_people_returns_no_value() {
        assertThat(new People().youngestPetOwner()).isEmpty();
    }

    @Test
    void person_without_pets() {
        Person glenn = new Person("Glenn", "Quagmire");
        assertThat(new People(glenn).youngestPetOwner()).hasValue(glenn);
        // this needs to be discussed with stakeholders. This could make more sense:
        // assertThat(new People(glenn).youngestPetOwner()).isEmpty();
    }

    @Test
    void one_person_with_pet() {
        Person peter = new Person("Peter", "Griffin", new Pet(CAT, "Tabby", 2));
        assertThat(new People(peter).youngestPetOwner()).hasValue(peter);
    }

    @Test
    void two_people_with_pets_of_different_age() {
        Person peter = new Person("Peter", "Griffin", new Pet(CAT, "Tabby", 2));
        Person lois = new Person("Lois", "Griffin", new Pet(SNAKE, "Serpy", 1));
        assertThat(new People(peter, lois).youngestPetOwner()).hasValue(lois);
        assertThat(new People(lois, peter).youngestPetOwner()).hasValue(lois);
    }

    @Test
    void two_people_with_pets_of_same_age() {
        Person meg = new Person("Meg", "Griffin", new Pet(BIRD, "Tweety", 1));
        Person lois = new Person("Lois", "Griffin", new Pet(SNAKE, "Serpy", 1));
        assertThat(new People(meg, lois).youngestPetOwner()).hasValue(meg);
        assertThat(new People(lois, meg).youngestPetOwner()).hasValue(lois);
    }

    @Test
    void who_owns_the_youngest_pet() {
        Person peter = new Person("Peter", "Griffin", new Pet(CAT, "Tabby", 2));
        Person stewie = new Person("Stewie", "Griffin",
            new Pet(CAT, "Dolly", 3),
            new Pet(DOG, "Brian", 9)
        );
        Person joe = new Person("Joe", "Swanson", new Pet(DOG, "Spike", 4));
        Person lois = new Person("Lois", "Griffin", new Pet(SNAKE, "Serpy", 1));
        Person meg = new Person("Meg", "Griffin", new Pet(BIRD, "Tweety", 1));
        Person chris = new Person("Chris", "Griffin", new Pet(TURTLE, "Speedy", 4));
        Person cleveland = new Person("Cleveland", "Brown",
            new Pet(HAMSTER, "Fuzzy", 1),
            new Pet(HAMSTER, "Wuzzy", 2)
        );
        Person glenn = new Person("Glenn", "Quagmire");

        People people = new People(peter, stewie, joe, lois, meg, chris, cleveland, glenn);
        Assertions.assertThat(people.youngestPetOwner()).hasValue(lois);
    }
}
