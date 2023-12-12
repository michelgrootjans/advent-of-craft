package greeting;

public class GreeterFactory {
    public static Greeter create(String formality) {
        return switch (formality) {
            case "formal" -> new FormalGreeter();
            case "casual" -> new CasualGreeter();
            case "intimate" -> new IntimateGreeter();
            default -> new DefaultGreeter();
        };
    }

    private GreeterFactory() {}
}