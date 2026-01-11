🏧 ATM Application using JDBC (Console-Based)

📌 Project Description
This is a console-based ATM mini project developed using Java and JDBC.
The application demonstrates how Java connects to a MySQL database using JDBC to securely store, retrieve, and update user account data such as account number, PIN, and balance.
This project focuses purely on backend logic and database connectivity, with no frontend/UI.


🎯 Objective

The main goal of this project is to understand:
How JDBC works
How Java interacts with a relational database
How to perform CRUD operations using SQL
How banking operations are handled at the backend level

🛠️ Technologies Used

Java
JDBC (Java Database Connectivity)
MySQL
Eclipse IDE
Git & GitHub

🗄️ Database Connectivity (JDBC)

The application uses JDBC API to connect Java with MySQL.
Database credentials are securely accessed using environment variables:
DB_URL
DB_USERNAME
DB_PASSWORD
DriverManager is used to establish the database connection.
PreparedStatement is used to prevent SQL Injection.

📋 Features

✔ Create a new bank account
✔ Check account balance
✔ Deposit money
✔ Withdraw money
✔ PIN-based authentication
✔ Data stored permanently in MySQL

🧾 Database Table Structure
CREATE TABLE atm (
    acc_no INT PRIMARY KEY,
    name VARCHAR(50),
    pin INT,
    balance DOUBLE
);

⚙️ How the Application Works

User selects an option from the ATM menu.
Java application takes input using Scanner.
JDBC connects to the MySQL database.
SQL queries are executed using PreparedStatement.
Results are fetched using ResultSet.
Data is updated and stored securely in the database.

🔐 Security Practices Used

Prepared Statements (prevents SQL Injection)
Environment variables for database credentials
PIN verification for sensitive operations

🚀 How to Run the Project

Clone the repository
Create the database and table in MySQL
Set environment variables for DB credentials
Add MySQL Connector/J to the project
Run the Java file from Eclipse or terminal

📚 Learning Outcomes

Hands-on experience with JDBC
Understanding backend banking logic
Writing clean and secure SQL queries
Real-time Java–Database interaction

👨‍💻 Author

Venkat Prasad
(Beginner Level Project)
