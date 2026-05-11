package com.data_access;

import com.data_management.DataStorage;

/**
 * Sends parsed data to the data storage system.
 */
public class DataSourceAdapter {

    private DataStorage dataStorage;

    /**
     * Creates a DataSourceAdapter object.
     *
     * @param dataStorage the data storage system
     */
    public DataSourceAdapter(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

     /**
     * Sends parsed data to storage.
     *
     * @param parsedData the parsed data
     */
    public void sendToStorage(String parsedData) {

        System.out.println(
                "Sending parsed data to storage: " + parsedData);
    }
}