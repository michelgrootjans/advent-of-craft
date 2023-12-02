package games;

public class FizzBuzz {
    private FizzBuzz() {
    }
    
    public static String convert(Integer input) {
        if (input <= 0) throw new OutOfRangeException();
        if (input > 100) throw new OutOfRangeException();

        if (input % 3 == 0 && input % 5 == 0) return "FizzBuzz";
        if (input % 3 == 0) return "Fizz";
        if (input % 5 == 0) return "Buzz";
        else return input.toString();
    }
}
