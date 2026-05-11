package com.data_access;

/**
 * Receives data from WebSocket connections.
 */
public class WebSocketDataListener implements DataListener {

    /**
     * Receives WebSocket data.
     *
     * @param rawData the incoming raw data
     */
    @Override
    public void receiveData(String rawData) {

        System.out.println("Receiving WebSocket data: " + rawData);
    }
}