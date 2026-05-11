# Generator

The Generator Subsystem creates simulated patient health data for the system. The `PatientDataGenerator` interface is used as a common structure for all generator classes. Different classes generate different types of patient data, such as ECG data, blood pressure, blood oxygen saturation, blood levels, and alert events.

The `HealthDataSimulator` class controls the subsystem. It creates generator objects and schedules them to run repeatedly for each patient. Using separate generator classes makes the system easier to organize and maintain because each class has its own responsibility.

The subsystem also uses the `OutputStrategy` interface to send generated data to different outputs. This allows the generators to work without depending on a specific communication method. Overall, the subsystem shows modular design, flexibility, and separation of responsibilities.


# Output 

The Output sends generated patient data to different destinations. The subsystem uses the `OutputStrategy` interface, which defines a common `output()` method for all output classes.

Different implementations provide different ways to send data. `ConsoleOutputStrategy` prints data to the console, `FileOutputStrategy` saves data to files, `TcpOutputStrategy` sends data through TCP connections, and `WebSocketOutputStrategy` broadcasts data to connected WebSocket clients.

The `HealthDataSimulator` class selects which output strategy will be used. Because the simulator works with the interface instead of specific classes, new output methods can be added easily later. This subsystem demonstrates flexibility, modularity, and separation between data generation and communication logic.


# Data Management 

The Data Management stores and organizes patient information. The `DataStorage` class manages all patient data and provides methods for adding and retrieving records.

`DataStorage` contains many `Patient` objects, and each `Patient` contains many `PatientRecord` objects. This structure represents how patient information is organized in the system.

The `Patient` class manages a patient’s records, while the `PatientRecord` class stores information about a single measurement, such as the record type, value, and timestamp.

The subsystem also includes the `DataReader` interface, which separates data reading from data storage. This makes the system easier to extend and maintain. Overall, the subsystem demonstrates good organization and clear responsibilities.


# Alerts 

The Alerts check patient data and create alerts when certain conditions are detected. The subsystem is built around the `AlertGenerator` class, which uses `DataStorage` to access patient information.

The `AlertGenerator` class evaluates `Patient` objects and creates `Alert` objects when needed. The `Alert` class stores information such as patient ID, alert condition, and timestamp.

This subsystem keeps alert logic separate from data generation and storage logic. This makes the system easier to maintain and improve in the future. The subsystem also represents how healthcare systems monitor patient data and detect important medical events.