package people;

public record Person(String firstName, String lastName, Pets pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, new Pets());
    }

    public Person addPet(PetType petType, String name, int age) {
        return new Person(firstName, lastName, pets.add(new Pet(petType, name, age)));
    }

    public int youngestPetAge() {
        return pets.youngestPetAge();
    }
}
