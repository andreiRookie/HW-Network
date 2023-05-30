package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.example.Config.ENTER_FIRST_CITY;

public class Server {

    public static void main(String[] args) {

        boolean isFirstCity = true;
        String currentCity = "";
        String inputCity;

        try (ServerSocket serverSocket = new ServerSocket(Config.PORT);) {
            System.out.println("Server started");

            while (true) {
                try (Socket client = serverSocket.accept();
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    if (isFirstCity) {
                        out.println(ENTER_FIRST_CITY);

                        inputCity = in.readLine();
                        System.out.println(String.format("Client sent: %s", inputCity));

                        currentCity = inputCity;
                        isFirstCity = false;
                    } else {
                        out.println(currentCity);

                        inputCity = in.readLine();
                        System.out.println(String.format("Client sent: %s", inputCity));

                        if (checkInput(inputCity, currentCity)) {
                            currentCity = inputCity;
                            out.println("OK");
                        } else if (!checkInput(inputCity, currentCity)) {
                            out.println("NOT OK");
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Try again");
                    ex.printStackTrace();
                }

            }
        } catch (IOException ex) {
            System.out.println("Server not started");
            ex.printStackTrace();
        }
    }

    private static boolean checkInput(String inputCity, String currentCity) {
        char inputFirstChar = inputCity.charAt(0);
        char currentLastChar = currentCity.charAt(currentCity.length() - 1);

        return inputFirstChar == currentLastChar;
    }
}
