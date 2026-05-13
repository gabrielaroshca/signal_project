package com.data_management;

import java.io.IOException;

/**
 * Interface for reading patient data into DataStorage.
 *
 * Implementations can read data from files, WebSocket streams,
 * or other real-time sources.
 */
public interface DataReader {

    /**
     * Reads data into DataStorage.
     *
     * @param dataStorage the storage system
     * @throws IOException if data cannot be read
     */
    void readData(DataStorage dataStorage) throws IOException;

    /**
     * Starts continuous real-time data reading.
     *
     * This is mainly used by WebSocket-based readers.
     *
     * @param dataStorage the storage system
     * @throws IOException if the stream cannot start
     */
    default void startRealTimeReading(DataStorage dataStorage)
            throws IOException {

        readData(dataStorage);
    }
}