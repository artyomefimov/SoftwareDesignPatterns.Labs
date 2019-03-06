package lab2.proxy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class NumbersMultiplier {
    private Socket fromServer;
    private BufferedReader reader;
    private PrintWriter writer;

    public Float multiply(List<Float> numbers) throws IOException {
        return requestResultFromServer(numbers);
    }

    private Float requestResultFromServer(List<Float> numbers) throws IOException {
        try {
            connectToServer();

            for (Float number : numbers) {
                writer.println(number);
            }

            writer.println(Client.END_INPUT);

            String resultFromServer = reader.readLine();
            return Float.parseFloat(resultFromServer);

        } catch (IOException e) {
            System.out.println("Could not connect to localhost:5000");
            throw e;
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
            if (fromServer != null)
                fromServer.close();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("Connecting to localhost:5000");

        fromServer = new Socket("localhost", 5000);

        System.out.println("Connected to localhost:5000");

        reader = new BufferedReader(new InputStreamReader(fromServer.getInputStream()));
        writer = new PrintWriter(fromServer.getOutputStream(), true);
    }
}
