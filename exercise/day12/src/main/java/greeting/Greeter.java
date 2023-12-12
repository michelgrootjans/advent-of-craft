package greeting;

public class Greeter {
    String formality;
    IGreeter greeter;

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
        }
        return greeter.greet();
    }

    public void setFormality(String formality) {
        this.formality = formality;
        this.greeter = findGreeter(formality);
    }

    private IGreeter findGreeter(String formality) {
        if (formality.equals("formal")) {
            return new FormalGreeter();
        } else if (formality.equals("casual")) {
            return new CasualGreeter();
        } else if (formality.equals("intimate")) {
            return new IntimateGreeter();
        }
        return new DefaultGreeter();
    }
}
