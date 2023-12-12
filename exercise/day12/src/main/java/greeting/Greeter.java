package greeting;

public class Greeter {
    private IGreeter greeter = new DefaultGreeter();

    public String greet() {
        return greeter.greet();
    }

    public void setFormality(String formality) {
        this.greeter = findGreeter(formality);
    }

    private IGreeter findGreeter(String formality) {
        return switch (formality) {
            case "formal" -> new FormalGreeter();
            case "casual" -> new CasualGreeter();
            case "intimate" -> new IntimateGreeter();
            default -> new DefaultGreeter();
        };
    }
}
