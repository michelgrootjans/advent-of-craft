package greeting;

public class DefaultGreeter implements Greeter {
    @Override
    public String greet() {
        return "Hello.";
    }
}
