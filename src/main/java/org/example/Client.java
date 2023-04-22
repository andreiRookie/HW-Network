package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.Config.ENTER_FIRST_CITY;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket(Config.HOST, Config.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String kazan = "kazan";
            String novosibirsk = "novosibirsk";

            String response = reader.readLine();

            if (response.equals(ENTER_FIRST_CITY)) {
                writer.println(kazan);

            } else if (response.equals(kazan)) {
                writer.println(novosibirsk);
                System.out.println(String.format("Server response: %s", reader.readLine()));

            } else {
                writer.println(kazan);
                System.out.println(String.format("Server response: %s", reader.readLine()));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
