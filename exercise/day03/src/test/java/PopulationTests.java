import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import people.People;
import people.Person;
import people.PetType;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

class PopulationTests {
    @Test
    void noPeopleReturnsNoValue() {
        assertThat(new People().youngestPetOwner()).isEmpty();
    }

    @Test
    void personWithoutPets() {
        Person glenn = new Person("Glenn", "Quagmire");
        assertThat(new People(glenn).youngestPetOwner()).hasValue(glenn);
        // this needs to be discussed with stakeholders. This could make more sense:
        // assertThat(new People(glenn).youngestPetOwner()).isEmpty();
    }

    @Test
    void onePersonWithPet() {
        Person peter = new Person("Peter", "Griffin").addPet(PetType.CAT, "Tabby", 2);
        assertThat(new People(peter).youngestPetOwner()).hasValue(peter);
    }

    @Test
    void twoPeopleWithPetsOfDifferentAge() {
        Person peter = new Person("Peter", "Griffin").addPet(PetType.CAT, "Tabby", 2);
        Person lois = new Person("Lois", "Griffin").addPet(PetType.SNAKE, "Serpy", 1);
        assertThat(new People(peter, lois).youngestPetOwner()).hasValue(lois);
        assertThat(new People(lois, peter).youngestPetOwner()).hasValue(lois);
    }

    @Test
    void twoPeopleWithPetsOfSameAge() {
        Person meg = new Person("Meg", "Griffin").addPet(PetType.BIRD, "Tweety", 1);
        Person lois = new Person("Lois", "Griffin").addPet(PetType.SNAKE, "Serpy", 1);
        assertThat(new People(meg, lois).youngestPetOwner()).hasValue(meg);
        assertThat(new People(lois, meg).youngestPetOwner()).hasValue(lois);
    }

    @Test
    void whoOwnsTheYoungestPet() {
        Person peter = new Person("Peter", "Griffin")
            .addPet(PetType.CAT, "Tabby", 2);
        Person stewie = new Person("Stewie", "Griffin")
            .addPet(PetType.CAT, "Dolly", 3)
            .addPet(PetType.DOG, "Brian", 9);
        Person joe = new Person("Joe", "Swanson")
            .addPet(PetType.DOG, "Spike", 4);
        Person lois = new Person("Lois", "Griffin")
            .addPet(PetType.SNAKE, "Serpy", 1);
        Person meg = new Person("Meg", "Griffin")
            .addPet(PetType.BIRD, "Tweety", 1);
        Person chris = new Person("Chris", "Griffin")
            .addPet(PetType.TURTLE, "Speedy", 4);
        Person cleveland = new Person("Cleveland", "Brown")
            .addPet(PetType.HAMSTER, "Fuzzy", 1)
            .addPet(PetType.HAMSTER, "Wuzzy", 2);
        Person glenn = new Person("Glenn", "Quagmire");

        People people = new People(peter, stewie, joe, lois, meg, chris, cleveland, glenn);
        Assertions.assertThat(people.youngestPetOwner()).hasValue(lois);
    }
}
