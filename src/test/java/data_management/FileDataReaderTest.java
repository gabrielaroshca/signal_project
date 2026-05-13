package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.FileDataReader;

/**
 * Tests the FileDataReader class.
 *
 * These tests verify that:
 * - simulator files are read correctly
 * - patient data is parsed correctly
 * - parsed data is stored in DataStorage
 */
public class FileDataReaderTest {

    /**
     * Tests reading valid patient data from a file.
     *
     * The test:
     * - creates a temporary output directory
     * - writes sample simulator data into a file
     * - reads the file using FileDataReader
     * - checks that the data was stored correctly
     *
     * @throws IOException if the temporary file cannot be created
     */
    @Test
    public void testReadDataFromFile() throws IOException {

        // Create temporary directory for test files
        Path tempDir = Files.createTempDirectory("test-output");

        // Create sample output file
        Path file = tempDir.resolve("ECG.txt");

        // Write sample simulator data into the file
        Files.writeString(file, "Patient ID: 1, Timestamp: 1000, Label: ECG, Data: 0.75\n");

        // Create storage and reader objects
        DataStorage storage = new DataStorage();
        FileDataReader reader = new FileDataReader(tempDir.toString());

        // Read data from the file
        reader.readData(storage);

        // Verify that one record was stored
        assertEquals(1, storage.getRecords(1, 0, 2000).size());

        // Verify that the record type is correct
        assertEquals("ECG", storage.getRecords(1, 0, 2000).get(0).getRecordType());

        // Verify that the measurement value is correct
        assertEquals(0.75, storage.getRecords(1, 0, 2000).get(0).getMeasurementValue());
    }
}