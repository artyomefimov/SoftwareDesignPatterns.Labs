package lab2.adapter;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class StringWriter {
    private Adapter adapter;

    public StringWriter() {
        adapter = new Adapter();
    }

    public void writeStrings(String... strings) throws IOException {
        adapter.writeToByteStream(strings);
    }

    private class Adapter {
        public void writeToByteStream(String... strings) throws IOException {
            try (BufferedOutputStream outputStream = new BufferedOutputStream(System.out)){
                for (String string : strings) {
                    outputStream.write(string.getBytes());
                }
            }
        }
    }
}
