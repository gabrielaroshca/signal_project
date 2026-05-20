package data_acces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alerts.AlertGenerator;
import com.data_access.WebSocketDataClient;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Integration test for WebSocket processing,
 * data storage, and alert evaluation.
 */
public class WebSocketIntegrationTest {

    /**
     * Tests that WebSocket messages are parsed,
     * stored, and accessible for alert evaluation.
     */
    @Test
    public void testWebSocketToAlertIntegration()
            throws Exception {

        DataStorage storage = DataStorage.getInstance();
        storage.clear();

        WebSocketDataClient client = new WebSocketDataClient(new URI("ws://localhost:8080"), storage);

        String message = "Patient ID: 1, Timestamp: 1000, Label: HeartRate, Data: 120";

        client.processMessage(message);

        List<PatientRecord> records = storage.getRecords(1, 0, Long.MAX_VALUE);

        assertEquals(1, records.size());

        Patient patient = storage.getAllPatients().get(0);

        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(patient);

        assertEquals(1, patient.getRecords(0, Long.MAX_VALUE).size());
    }
}