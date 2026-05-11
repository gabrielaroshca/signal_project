package com.data_access;

/**
 * Receives data from files.
 */
public class FileDataListener implements DataListener {

    /**
     * Receives file data.
     *
     * @param rawData the incoming raw data
     */
    @Override
    public void receiveData(String rawData) {

        System.out.println("Receiving file data: " + rawData);
    }
}