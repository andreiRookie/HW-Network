package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket socket = new ServerSocket(Config.PORT);) {
            System.out.println("Server started");
            while (true) {
                try (Socket client = socket.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    String input = reader.readLine();

                    System.out.println(String.format("Client connected:\nInput: %s\n Port: %d", input, client.getPort()));

                    writer.println("Hello from server!");
                }

            }
        } catch (IOException ex) {
            System.out.println("Server not started");
            ex.printStackTrace();
        }
    }
}
