package data_acces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.data_access.WebSocketDataClient;
import com.data_management.DataStorage;
import com.data_management.PatientRecord;

/**
 * Tests the WebSocketDataClient class.
 */
public class WebSocketDataClientTest {

    /**
     * Tests that a valid WebSocket message
     * is parsed and stored correctly.
     */
    @Test
    public void testProcessValidMessage() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String message = "Patient ID: 1, " + "Timestamp: 1000, " + "Label: ECG, " + "Data: 75.5";

        client.processMessage(message);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(1, records.size());

        assertEquals(75.5, records.get(0).getMeasurementValue());

        assertEquals("ECG", records.get(0).getRecordType());
    }

    /**
     * Tests that corrupted messages
     * do not crash the client.
     */
    @Test
    public void testProcessInvalidMessage() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String invalidMessage = "INVALID DATA FORMAT";

        client.processMessage(invalidMessage);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(0, records.size());
    }

    /**
     * Tests that a message with missing fields
     * is skipped safely.
     */
    @Test
    public void testMessageWithMissingFieldsIsSkipped() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String incompleteMessage = "Patient ID: 1, Timestamp: 1000";

        client.processMessage(incompleteMessage);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(0, records.size());
    }

    /**
     * Tests that WebSocket errors are handled
     * without crashing the client.
     */
    @Test
    public void testWebSocketErrorHandling() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        client.onError(new Exception("Simulated connection error"));

        assertEquals(0, storage.getRecords(1, 0, Long.MAX_VALUE).size());
    }

    /**
     * Tests that an empty WebSocket message
     * does not crash the client.
     */
    @Test
    public void testEmptyMessageHandling() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String emptyMessage = "";

        client.processMessage(emptyMessage);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(0, records.size());
    }

    /**
     * Tests that messages containing invalid
     * numeric values are skipped safely.
     */
    @Test
    public void testInvalidNumberFormatHandling() throws Exception {

        DataStorage storage = new DataStorage();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String badNumber = "Patient ID: A, Timestamp: test, Label: ECG, Data: value";

        client.processMessage(badNumber);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(0, records.size());
    }
}