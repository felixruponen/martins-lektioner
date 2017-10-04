package com.martin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);

        try {
            while (true) {
                System.out.println("Listening to connection on port 9090");
                Socket socket = listener.accept();
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    String clientMessage = input.readLine();

                    output.println("Hello ChatClient! You wrote: " + clientMessage);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}
