package com.data_access;

/**
 * Receives data from TCP connections.
 */
public class TCPDataListener implements DataListener {

    /**
     * Receives TCP data.
     *
     * @param rawData the incoming raw data
     */
    @Override
    public void receiveData(String rawData) {

        System.out.println("Receiving TCP data: " + rawData);
    }
}