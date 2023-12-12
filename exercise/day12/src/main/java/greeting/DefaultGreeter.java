package greeting;

public class DefaultGreeter implements IGreeter {
    @Override
    public String greet() {
        return "Hello.";
    }
}
