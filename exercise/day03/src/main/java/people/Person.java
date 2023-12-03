package people;

public record Person(String firstName, String lastName, Pets pets) {
    public Person(String firstName, String lastName, Pet... pets) {
        this(firstName, lastName, new Pets(pets));
    }

    public Integer ageOfYoungestPet() {
        return pets.youngest()
            .map(Pet::age).orElse(Integer.MAX_VALUE);
    }
}
