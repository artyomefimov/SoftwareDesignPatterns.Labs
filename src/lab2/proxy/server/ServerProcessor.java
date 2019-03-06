package lab2.proxy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerProcessor {
    private static final String END_INPUT = "stop";

    public void processRequest() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Connected to the client\nWaiting for messages...");

            String input;
            float inNumber;

            ArrayList<Float> numbersToMultiply = new ArrayList<>();

            while ((input = reader.readLine()) != null) {
                if (END_INPUT.equals(input))
                    break;

                try {
                    inNumber = Float.parseFloat(input);
                    numbersToMultiply.add(inNumber);
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("Could not parse " + input + " to float");
                }
            }

            System.out.println("Got numbers: " + numbersToMultiply.toString());

            float result = countResult(numbersToMultiply);
            printResult(numbersToMultiply, result);

            System.out.println("Sending result: " + result);
            writer.println(result);
        }
    }

    private float countResult(List<Float> numbers) {
        if (numbers.size() == 0)
            return 0;

        float result = 1;
        for (float number : numbers) {
            result *= number;
        }
        return result;
    }

    private void printResult(List<Float> numbers, float result) {
        StringBuilder builder = new StringBuilder();
        for (float number : numbers) {
            builder.append(number).append(" * ");
        }
        builder.append(" = ").append(result);
        System.out.println(builder.toString());
    }
}
