package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Outputs generated patient health data through a TCP server connection.
 * Connected clients receive streamed health simulation data in real time.
 */

public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Creates a TCP output strategy using the specified server port.
     *
     * @param port the TCP port used for client connections
     */

    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends generated patient health data to the connected TCP client.
     *
     * @param patientId the ID of the patient associated with the data
     * @param timestamp the timestamp when the data was generated
     * @param label the category of generated health data
     * @param data the generated patient data value
     */

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
