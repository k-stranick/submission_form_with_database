package dtcc.itn261.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDate;


public class DatabaseManager {
    private static final String MY_SQL_URL = "jdbc:mysql://localhost:3306";
    private static final String CURRENT_DATABASE_URL = "jdbc:mysql://localhost:3306/information_form_schema";
    private static final String DB_NAME = "information_form_schema";
    private static final String TABLE_NAME = "personal_info";
    private static final String USER = "root";
    private static final String PASSWORD = "aq1SW@de3FR$";

    private DatabaseManager() {
    }

    /*
     * Setting up database link
     */

    private static Connection initialConnectToDatabase() throws SQLException {
        return DriverManager.getConnection(MY_SQL_URL, USER, PASSWORD);
    }

    private static Connection connectToSpecificDatabase() throws SQLException {
        return DriverManager.getConnection(CURRENT_DATABASE_URL, USER, PASSWORD);
    }

    /**
     * Method designed to see if the database or table exist and creates either if not
     */
    public static void initializeDatabase() {
        try (Connection dataBaseConnection = initialConnectToDatabase();
             Statement allowsStatementExecution = dataBaseConnection.createStatement()) {

            if (!doesDatabaseExists(dataBaseConnection)) {
                createDatabase(allowsStatementExecution);
                ExceptionAlert.showDataBaseCreationAlert();
                LogUtility.info("Database '" + DB_NAME + "' was created because it did not exist.");
            } else {
                LogUtility.info("Database '" + DB_NAME + "' already exists. No need to create.");
            }

            allowsStatementExecution.executeUpdate("USE " + DB_NAME);

            if (!doesTableExists(dataBaseConnection)) {
                createTable(allowsStatementExecution);
                insertSampleData(allowsStatementExecution);
                LogUtility.info("Table 'personal_info was created.");
                LogUtility.info("Sample data was created");
            } else {
                //drop the database each time?
                LogUtility.info("Table 'personal_info' already exists. No need to create.");
            }
        } catch (SQLException e) {
            ExceptionAlert.sqlErrorAlert("Error occurred while setting up database: " + e.getMessage());  // General alert for database creation issue
            LogUtility.error("An error occurred while setting up the database: " + e.getMessage(), e);
            ExceptionAlert.showSqlConnectionAlert();
        }
    }

    /**
     * Checks database existence.
     *
     * @param mySQLConn the connection to the MySQL database
     * @return true if the database exists, false otherwise
     * @throws SQLException if an error occurs while executing the SQL query
     */
    private static boolean doesDatabaseExists(Connection mySQLConn) throws SQLException {
        String query = "SELECT 1 FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";

        try (PreparedStatement preparedStatement = mySQLConn.prepareStatement(query)) {
            preparedStatement.setString(1, DatabaseManager.DB_NAME);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next(); // If there's a result, the database exists
            }
        }
    }

    /**
     * Creates a database if it does not exist.
     *
     * @param allowsStatementExecution the connection to the MySQL database
     * @throws SQLException if an error occurs while executing the SQL query
     */
    private static void createDatabase(Statement allowsStatementExecution) throws SQLException {
        //allowsStatementExecution.executeUpdate("DROP DATABASE IF EXISTS " + DB_NAME);
        // Create a database if it does not exist
        allowsStatementExecution.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
    }

    /**
     * Checks if the specified table exists in the database.
     *
     * @param mySQLConn the connection to the MySQL database
     * @return true if the table exists, false otherwise
     * @throws SQLException if an error occurs while executing the SQL query
     */
    private static boolean doesTableExists(Connection mySQLConn) throws SQLException {
        String query = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

        try (PreparedStatement preparedStatement = mySQLConn.prepareStatement(query)) {
            preparedStatement.setString(1, DatabaseManager.DB_NAME);
            preparedStatement.setString(2, DatabaseManager.TABLE_NAME);
            try (ResultSet tableExistence = preparedStatement.executeQuery()) {

                return tableExistence.next();
            }
        }
    }

    /**
     * Creates a database table if it does not exist.
     *
     * @param allowsStatementExecution the connection to the MySQL database
     * @throws SQLException if an error occurs while executing the SQL query
     */
    private static void createTable(Statement allowsStatementExecution) throws SQLException {
        // Create a table if it does not exist
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS personal_info (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "firstName VARCHAR(20) NOT NULL," +
                "lastName VARCHAR(20) NOT NULL," +
                "phoneNumber VARCHAR(15) UNIQUE," +
                "birthday DATE," +
                "email VARCHAR(255) NOT NULL UNIQUE," +
                "address VARCHAR(255)," +
                "city VARCHAR(255)," +
                "state VARCHAR(255)," +
                "zipCode INT," +
                "notes TEXT," +
                "CONSTRAINT chk_phone_number CHECK (phoneNumber REGEXP '^\\\\d{10}$|^((\\\\d{3}-){2}\\\\d{4})$|^\\\\(\\\\d{3}\\\\) \\\\d{3}-\\\\d{4}$|^\\\\d{3}-\\\\d{7}$' OR phoneNumber IS NULL)," +
                "CONSTRAINT chk_email_format CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$')" +
                ") ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_UNICODE_CI;";

        allowsStatementExecution.executeUpdate(sqlCreateTable);
    }

    public static void insertSampleData(Statement allowsStatementExecution) throws SQLException{
        String insertSql = "INSERT INTO personal_info (firstName, lastName, phoneNumber, birthday, email, address, city, state, zipCode, notes) VALUES " +
                "('John', 'Doe', '123-4567890', '1990-05-15', 'john.doe@example.com', '1234 Elm St', 'Anytown', 'CA', 90210, 'No additional notes.'), " +
                "('Jane', 'Smith', '987-6543210', '1988-07-20', 'jane.smith@example.com', '5678 Oak St', 'Othertown', 'TX', 75001, 'Prefers email contact.'), " +
                "('Alice', 'Johnson', '555-1234567', '1992-03-05', 'alice.johnson@example.com', '910 Pine Ave', 'Smallville', 'NY', 10001, 'Interested in newsletters.'), " +
                "('Bob', 'Brown', '444-7654321', '1985-09-15', 'bob.brown@example.com', '135 Maple Dr', 'Bigtown', 'FL', 33101, 'No additional notes.'), " +
                "('Carol', 'Davis', '333-6789012', '1991-12-10', 'carol.davis@example.com', '246 Spruce Way', 'Laketown', 'NV', 89501, 'Allergic to peanuts.'), " +
                "('David', 'Wilson', '222-9876543', '1984-11-20', 'david.wilson@example.com', '579 Birch Pl', 'Hilltown', 'NC', 28201, 'Volunteer.'), " +
                "('Eve', 'Moore', '111-2468013', '1993-01-30', 'eve.moore@example.com', '802 Cedar Ln', 'Rivertown', 'WA', 98101, 'Frequent traveler.'), " +
                "('Frank', 'Taylor', '666-1357902', '1979-08-25', 'frank.taylor@example.com', '425 Elmwood St', 'Beachtown', 'OR', 97001, 'Interested in tech updates.'), " +
                "('Grace', 'Anderson', '777-2468135', '1975-06-07', 'grace.anderson@example.com', '548 Pinecone Rd', 'Mountainview', 'CO', 80201, 'Crafting enthusiast.'), " +
                "('Henry', 'Thomas', '888-9753102', '1995-04-12', 'henry.thomas@example.com', '367 Oakwood Ave', 'Valleytown', 'PA', 19101, 'Gamer and tech lover.');";

    allowsStatementExecution.executeUpdate(insertSql);
    }

    /**
     * Fetches data from the database and returns it as an ObservableList of FormData objects.
     *
     * @return an ObservableList of FormData objects containing the data fetched from the database
     */
    public static ObservableList<FormData> fetchDataFromDatabase() {
        ObservableList<FormData> dataList = FXCollections.observableArrayList();
        String selectSQL = "SELECT * FROM personal_info";
        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement selectAllStmt = conn.prepareStatement(selectSQL);
             ResultSet returnedQueryResults = selectAllStmt.executeQuery()) {
            while (returnedQueryResults.next()) { //try/catch?
                dataList.add(extractFormData(returnedQueryResults));
            }
        } catch (SQLException ex) {
            LogUtility.error("An error occurred while setting up the database: " + ex.getMessage());
            ExceptionAlert.sqlErrorAlert("Error: " + ex.getMessage());
        }

        return dataList;
    }

    /**
     * Fetches data from the database by ID.
     *
     * @param id the ID of the record to be fetched
     * @return the FormData object containing the data fetched by ID, or null if no data is found
     */
    public static FormData fetchDataById(int id) {
        String selectSQL = "SELECT * FROM personal_info WHERE id = ? ";

        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement protectedSQLStatement = conn.prepareStatement(selectSQL)) {
            protectedSQLStatement.setInt(1, id);  // Setting the ID parameter
            ResultSet selectWhereIdMatchesResult = protectedSQLStatement.executeQuery();

            if (selectWhereIdMatchesResult.next()) {
                return extractFormData(selectWhereIdMatchesResult);
            }
        } catch (SQLException ex) {
            LogUtility.error("Error fetching data by ID: " + ex.getMessage());
            ExceptionAlert.showSqlQueryAlert();
        }

        return null;
    }

    /**
     * Extracts a FormData object from a single entry ResultSet.
     *
     * @param singleEntryResultSet the ResultSet containing a single entry
     * @return the FormData object extracted from the ResultSet
     * @throws SQLException if an error occurs while extracting the data
     */
    private static FormData extractFormData(ResultSet singleEntryResultSet) throws SQLException {
        FormData data = new FormData();
        data.setId(singleEntryResultSet.getInt("id"));
        data.setFirstName(singleEntryResultSet.getString("firstName"));
        data.setLastName(singleEntryResultSet.getString("lastName"));
        data.setPhone(singleEntryResultSet.getString("phoneNumber"));
        data.setBirthday(LocalDate.parse(singleEntryResultSet.getString("birthday")));
        data.setEmail(singleEntryResultSet.getString("email"));
        data.setAddress(singleEntryResultSet.getString("address"));
        data.setCity(singleEntryResultSet.getString("city"));
        data.setState(singleEntryResultSet.getString("state"));
        data.setZipCode(singleEntryResultSet.getString("zipCode"));
        data.setNotes(singleEntryResultSet.getString("notes"));

        return data;
    }

    /**
     * Updates the TableView with the latest data fetched from the database.
     *
     * @param tableView the TableView to be updated
     */
    public static void updateTableView(TableView<FormData> tableView) {
        ObservableList<FormData> updatedData = DatabaseManager.fetchDataFromDatabase();
        tableView.setItems(updatedData);
        tableView.refresh();
    }

    /**
     * Inserts a new record into the database.
     *
     * @param data the FormData object containing the data to be inserted
     */
    public static void insertRecord(FormData data) {
        if (duplicateEntryInDatabase(data.getEmail(), data.getPhone())) {
            LogUtility.error("Failed insertion to the database");
            ExceptionAlert.showFailedInsertionAlert();

            return; // Skip insertion because of duplication
        }
        // Show user an alert if the user was created??
        String insertSqlStatement = "INSERT INTO personal_info (firstName, lastName, phoneNumber, birthday, email, " +
                "address, city, state, zipCode, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement preparedSqlStatement = conn.prepareStatement(insertSqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedSqlStatement.setString(1, data.getFirstName());
            preparedSqlStatement.setString(2, data.getLastName());
            preparedSqlStatement.setString(3, data.getPhone());
            preparedSqlStatement.setDate(4, Date.valueOf(data.getBirthday())); // Assuming getBirthday returns a LocalDate
            preparedSqlStatement.setString(5, data.getEmail());
            preparedSqlStatement.setString(6, data.getAddress());
            preparedSqlStatement.setString(7, data.getCity());
            preparedSqlStatement.setString(8, data.getState());
            preparedSqlStatement.setString(9, data.getZipCode());
            preparedSqlStatement.setString(10, data.getNotes());

            int insertedRows = preparedSqlStatement.executeUpdate();
            if (insertedRows == 0) {
                ExceptionAlert.showSqlQueryAlert();
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            LogUtility.error(e.getMessage());
        }
    }

    /**
     * Checks if the specified email or phone number already exists in the database.
     *
     * @param email       the email to be checked for duplication
     * @param phoneNumber the phone number to be checked for duplication
     * @return true if there is at least one match, false otherwise
     */
    public static boolean duplicateEntryInDatabase(String email, String phoneNumber) {
        String query = "SELECT COUNT(*) FROM personal_info WHERE email = ? OR phoneNumber = ?";
        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement checkDuplicationStatement = conn.prepareStatement(query)) {
            checkDuplicationStatement.setString(1, email);
            checkDuplicationStatement.setString(2, phoneNumber);
            ResultSet resultFromCheckQuery = checkDuplicationStatement.executeQuery();
            if (resultFromCheckQuery.next()) {

                return resultFromCheckQuery.getInt(1) > 0;  // true if there is at least one match
            }
        } catch (SQLException e) {
            LogUtility.error("Database check error: " + e.getMessage());

            return false;
        }

        return false;
    }

    /**
     * Updates the record in the database.
     *
     * @param data        the FormData object containing the updated data
     * @param searchBarId the ID of the record to be updated, obtained from the search bar
     */
    public static void updateRecord(FormData data, int searchBarId) {
        // Check if the ID from the search bar matches the ID in the FormData object
        if (data.getId() != searchBarId) {
            LogUtility.error("Error: ID mismatch. Expected: " + data.getId() + ", Found: " + searchBarId);

            return; // Stop the update if IDs do not match
        }

        // SQL statement for updating the record
        String updateSqlStatement = "UPDATE personal_info SET id = ?, firstName = ?, lastName = ?, phoneNumber = ?, birthday = ?, email = ?, address = ?, city = ?, state = ?, zipCode = ?, notes = ? WHERE id = ?";
        // Using try-with-resources to ensure that all resources are closed properly
        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement updateStatement = conn.prepareStatement(updateSqlStatement)) {

            // Setting parameters for the prepared statement
            updateStatement.setInt(1, data.getId());
            updateStatement.setString(2, data.getFirstName());
            updateStatement.setString(3, data.getLastName());
            updateStatement.setString(4, data.getPhone());
            updateStatement.setDate(5, Date.valueOf(data.getBirthday()));
            updateStatement.setString(6, data.getEmail());
            updateStatement.setString(7, data.getAddress());
            updateStatement.setString(8, data.getCity());
            updateStatement.setString(9, data.getState());
            updateStatement.setString(10, data.getZipCode());
            updateStatement.setString(11, data.getNotes());
            updateStatement.setInt(12, searchBarId); // Set the ID for the WHERE clause

            // Execute the update
            int affectedRows = updateStatement.executeUpdate();
            if (affectedRows == 0) {
                ExceptionAlert.invalidIdFormat("User not updated cannot change ID.");
            } else {
                ExceptionAlert.successfulUpdate(data.getId());
            }

        } catch (SQLException e) {
            LogUtility.error("SQL Error: " + e.getMessage());
        } catch (RuntimeException e) {
            LogUtility.error("Update Error: " + e.getMessage());
            ExceptionAlert.sqlErrorAlert("Update Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a record from the database.
     *
     * @param id the ID of the record to be deleted
     * @return true if the record is successfully deleted, false otherwise
     */
    public static boolean deleteRecord(int id) {
        String deleteSqlStatement = "DELETE FROM personal_info WHERE id = ?";
        try (Connection conn = connectToSpecificDatabase();
             PreparedStatement preparedSqlStatement = conn.prepareStatement(deleteSqlStatement)) {
            preparedSqlStatement.setInt(1, id);
            return preparedSqlStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LogUtility.error("Delete Error: " + e.getMessage());
        }

        return false;
    }
}
