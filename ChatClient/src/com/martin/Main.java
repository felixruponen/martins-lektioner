package com.martin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    /*
     *  Ex. $ > ChatClient 127.0.0.1
     */
    public static void main(String[] args) throws IOException {
        // Throw exception if input is incorrect
        if(args.length < 2) {
            throw new IllegalArgumentException("You must specify a server address and server port");
        }

        // Get address and port from args
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        // Open socket connection to address + port
        Socket socket = new Socket(serverAddress, serverPort);

        BufferedReader input = null;
        PrintWriter output = null;

        // Initialize I/O streams
        output = new PrintWriter(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Print status
        System.out.println("Connected to server " + serverAddress + " on port " + serverPort + " is " + socket.isConnected());

        // Write a string to the output stream
        output.println("GET / HTTP/1.1");
        output.println("Host: www.aftonbladet.se");
        output.println("");
        output.flush();

        // Read a line from the input stream
        String line = null;

        while( (line = input.readLine()) != null) {
            // Print the read line
            System.out.println(line);
        }

        System.exit(0);
    }
}
