package com.data_access;

/**
 * Interface for listening to incoming data.
 */
public interface DataListener {

    /**
     * Receives raw incoming data.
     *
     * @param rawData the incoming raw data
     */
    void receiveData(String rawData);
}