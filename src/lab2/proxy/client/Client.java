package lab2.proxy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Client {
    static final String END_INPUT = "stop";

    public static void main(String[] args) throws IOException {
        try (BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in))) {

            List<Float> numbersToMultiply = new ArrayList<>();
            readUserNumbers(numbersToMultiply, userInputReader);
            sendNumbersAndGetResult(numbersToMultiply);
        }
    }

    private static void readUserNumbers(List<Float> numbers, BufferedReader userInputReader) throws IOException {
        String userInput;
        System.out.println("Enter floats or \'stop\' command.");
        while ((userInput = userInputReader.readLine()) != null) {
            if (END_INPUT.equals(userInput))
                break;

            try {
                float userNumber = Float.parseFloat(userInput);
                System.out.println(userNumber + " is a good float");
                numbers.add(userNumber);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Could not parse " + userInput + " to float. Enter another number or \'stop\' command.");
            }
        }
    }

    private static void sendNumbersAndGetResult(List<Float> numbers) throws IOException {
        NumbersMultiplier multiplier = new NumbersMultiplier();
        float result = multiplier.multiply(numbers);
        printResult(numbers, result);
    }

    private static void printResult(List<Float> numbers, float result) {
        StringBuilder builder = new StringBuilder();
        for (float number : numbers) {
            builder.append(number).append(" * ");
        }
        builder.append(" = ").append(result);
        System.out.println(builder.toString());
    }
}
