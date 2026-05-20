# Patient Identification

The Patient Identification subsystem is used to keep track of patients and their health data. Each patient has a unique ID so the system knows which generated data belongs to which patient. This is important because the simulator creates health information for multiple patients at the same time.

The main classes are `Patient`, `PatientRecord`, and `DataStorage`. `Patient` stores information about a patient, while `PatientRecord` stores a single health measurement with a timestamp. `DataStorage` keeps all patient data organized so it can be accessed later.

This subsystem helps prevent data from getting mixed up and makes it easier to manage multiple patients in the system.

# Data Access Layer

The Data Access Layer is responsible for receiving and storing incoming data. The `DataListener` interface allows different listener classes, such as `FileDataListener`, `TCPDataListener`, and `WebSocketDataListener`, to receive data from different sources.

When data is received, it goes through the `DataSourceAdapter`, which connects the incoming data to the storage system. `DataParser` helps process the raw data before it is stored in `DataStorage`.

This structure keeps the system organized because each class has a separate responsibility, making the program easier to understand and update.


# Data Management 

The Data Management stores and organizes patient information. The `DataStorage` class manages all patient data and provides methods for adding and retrieving records.

`DataStorage` contains many `Patient` objects, and each `Patient` contains many `PatientRecord` objects. This structure represents how patient information is organized in the system.

The `Patient` class manages a patient’s records, while the `PatientRecord` class stores information about a single measurement, such as the record type, value, and timestamp.

The subsystem also includes the `DataReader` interface, which separates data reading from data storage. This makes the system easier to extend and maintain. Overall, the subsystem demonstrates good organization and clear responsibilities.


# Alerts 

The Alerts check patient data and create alerts when certain conditions are detected. The subsystem is built around the `AlertGenerator` class, which uses `DataStorage` to access patient information.

The `AlertGenerator` class evaluates `Patient` objects and creates `Alert` objects when needed. The `Alert` class stores information such as patient ID, alert condition, and timestamp.

This subsystem keeps alert logic separate from data generation and storage logic. This makes the system easier to maintain and improve in the future. The subsystem also represents how healthcare systems monitor patient data and detect important medical events.