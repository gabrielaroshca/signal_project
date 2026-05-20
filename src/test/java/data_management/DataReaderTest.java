package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.data_management.DataReader;
import com.data_management.DataStorage;

/**
 * Tests the modified DataReader interface.
 */
public class DataReaderTest {

    /**
     * Tests that startRealTimeReading uses readData
     * by default.
     */
    @Test
    public void testStartRealTimeReadingUsesReadData()
            throws IOException {

        DataStorage storage = DataStorage.getInstance();
        storage.clear();

        DataReader reader = new DataReader() {

            @Override
            public void readData(DataStorage dataStorage)
                    throws IOException {

                dataStorage.addPatientData(1, 88.0, "HeartRate", 1000L);
            }
        };

        reader.startRealTimeReading(storage);

        assertEquals(1, storage.getRecords(1, 0, Long.MAX_VALUE).size());

        assertEquals("HeartRate", storage.getRecords(1, 0, Long.MAX_VALUE).get(0).getRecordType());
    }
}