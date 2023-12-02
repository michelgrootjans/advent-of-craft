package games;

public class FizzBuzz {
    private FizzBuzz() {
    }
    
    public static String convert(Integer input) {
        if (input <= 0) throw new OutOfRangeException();
        if (input > 100) throw new OutOfRangeException();

        if (isFizz(input) && isBuzz(input)) return "FizzBuzz";
        if (isFizz(input)) return "Fizz";
        if (isBuzz(input)) return "Buzz";
        else return input.toString();
    }

    private static boolean isFizz(Integer input) {
        return input % 3 == 0;
    }

    private static boolean isBuzz(Integer input) {
        return input % 5 == 0;
    }
}
