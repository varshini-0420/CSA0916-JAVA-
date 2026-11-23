# CSA09 – Assignment 5
## File Handling, JDBC Performance and Database Migration

### Project Overview
This assignment demonstrates Java file handling, buffered I/O, database connectivity, SQL injection prevention, JDBC performance testing, transaction management, and file-to-database migration.

### Technologies Used
- Java
- MySQL
- JDBC
- MySQL Connector/J
- File Handling
- VS Code
- Command Prompt

### Tasks Completed

#### Task 1 – File I/O Performance Testing
- Compared buffered and unbuffered file reading.
- Measured file reading performance.
- Tested record searching with different dataset sizes.

#### Task 2 – Login Security Testing
- Implemented normal login verification.
- Demonstrated SQL injection using unsafe SQL queries.
- Used PreparedStatement to prevent SQL injection.

#### Task 3 – JDBC Performance Comparison
- Compared Statement and PreparedStatement.
- Tested auto-commit and transaction-based insertion.
- Measured batch insertion performance.
- Demonstrated transaction rollback when an error occurs.

#### Task 4 – File-to-Database Migration
- Read bus-pass records from a text file.
- Validated input records.
- Inserted valid records into MySQL using batch processing.
- Rejected invalid and duplicate records.
- Verified migrated records using SQL queries.

### Database Details
- Database Name: csa09_b2_testdb
- Table Name: bus_pass
- Database Management System: MySQL

### How to Execute
1. Install Java JDK.
2. Install MySQL Server.
3. Add MySQL Connector/J to the Java classpath.
4. Create the required database and tables.
5. Open the respective task folder in Command Prompt.
6. Compile the Java program using javac.
7. Run the program using java.

### Conclusion
This assignment provided practical experience in Java file handling, JDBC connectivity, database performance evaluation, secure SQL operations, transaction management, and reliable data migration.