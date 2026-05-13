package com.data_access;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.data_management.DataStorage;

/**
 * WebSocket client that receives real-time patient data.
 *
 * Incoming messages are parsed and stored in DataStorage.
 */
public class WebSocketDataClient extends WebSocketClient {

    private DataStorage dataStorage;

    /**
     * Creates a WebSocketDataClient.
     *
     * @param serverUri   the WebSocket server URI
     * @param dataStorage the storage system used to save patient data
     */
    public WebSocketDataClient(URI serverUri, DataStorage dataStorage) {

        super(serverUri);
        this.dataStorage = dataStorage;
    }

    /**
     * Runs when the WebSocket connection opens.
     *
     * @param handshakedata server handshake data
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server");
    }

    /**
     * Runs whenever a message is received from the server.
     *
     * @param message the incoming WebSocket message
     */
    @Override
    public void onMessage(String message) {
        processMessage(message);
    }

    /**
     * Runs when the WebSocket connection closes.
     *
     * @param code   close code
     * @param reason close reason
     * @param remote true if closed by remote server
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {

        System.out.println("WebSocket connection closed: " + reason);
    }

    /**
     * Runs when a WebSocket error occurs.
     *
     * @param ex the exception that occurred
     */
    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    /**
     * Processes one incoming message.
     *
     * This method is public so it can be tested without
     * needing a real WebSocket server.
     *
     * Expected format:
     * Patient ID: 1, Timestamp: 1000, Label: ECG, Data: 0.75
     *
     * @param message the incoming message
     */
    public void processMessage(String message) {

        try {
            String[] parts = message.split(",");

            int patientId = Integer.parseInt(parts[0].replace("Patient ID:", "").trim());

            long timestamp = Long.parseLong(parts[1].replace("Timestamp:", "").trim());

            String label = parts[2].replace("Label:", "").trim();

            String dataText = parts[3].replace("Data:", "").replace("%", "").trim();

            double value = Double.parseDouble(dataText);

            dataStorage.addPatientData(patientId, value, label, timestamp);

        } catch (Exception e) {
            System.err.println("Invalid WebSocket message skipped: " + message);
        }
    }
}