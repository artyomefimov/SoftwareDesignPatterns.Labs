package lab2.proxy.server;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerProcessor processor = new ServerProcessor();
        processor.processRequest();
    }
}
