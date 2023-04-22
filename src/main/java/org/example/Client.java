package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket(Config.HOST, Config.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println(String.format("Server said: %s", reader.readLine()));

            writer.println("Hello from client");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
