package greeting;

public class Greeter {
    String formality;

    public String greet() {
        if (this.formality == null) {
            return "Hello.";
        }

        if (this.formality.equals("formal")) {
            return new FormalGreeter().greet();
        } else if (this.formality.equals("casual")) {
            return new CasualGreeter().greet();
        } else if (this.formality.equals("intimate")) {
            return new IntimateGreeter().greet();
        } else {
            return new DefaultGreeter().greet();
        }
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }
}
