package greeting;

public class Greeter {
    String formality;
    private IGreeter greeter = new DefaultGreeter();

    public String greet() {
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
