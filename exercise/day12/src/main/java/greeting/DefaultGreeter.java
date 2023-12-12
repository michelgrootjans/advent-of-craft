package greeting;

class DefaultGreeter implements Greeter {
    @Override
    public String greet() {
        return "Hello.";
    }
}
