Java Library System
This repository contains the Java Library System, a project developed to manage library operations, including books, patrons, and loans. It features a well-organized architecture with modular components for commands, data handling, graphical user interface (GUI), and testing.

📋 Project Overview
Purpose:
To implement a library management system using Java, showcasing object-oriented programming (OOP) principles, modular design, and GUI implementation. The project handles common library operations like managing books, patrons, and loans.

Features:

Add, update, and remove books, patrons, and loans.
Search for books and patrons.
GUI for easy interaction with the library system.
Persistent data storage using books.txt, loans.txt, and patrons.txt.
📂 File Structure
Library/

.settings/: IDE settings (e.g., Eclipse)
bin/bcu/cmp5332/librarysystem: Compiled Java class files
commands/: Command processing classes
data/: Data handling and storage
gui/: Graphical user interface classes
main/: Main application logic
model/: Core models (e.g., Book, Patron, Loan)
test/: Unit tests for the system
resources/data/: Persistent data storage
books.txt: Data file for books
loans.txt: Data file for loans
patrons.txt: Data file for patrons
src/bcu/cmp5332/librarysystem: Java source files
commands/: Command processing logic
data/: Data handling and parsing
gui/: GUI implementation
main/: Main class and application logic
model/: Classes representing core entities
test/: Test cases for library features
.classpath: IDE-specific classpath file
.project: IDE-specific project file
.gitattributes: Git attributes configuration
🔧 How to Run
Clone the repository.
Open the project in an IDE:
Import the project into Eclipse, IntelliJ IDEA, or another Java IDE.
Ensure the classpath and project settings are configured correctly.
Compile and run:
Locate the main package in src/bcu/cmp5332/librarysystem/main.
Run the Main.java file to start the application.
🌟 Features in Detail
Book Management:
Add, update, search, and remove books in the library.
Patron Management:
Manage library patrons, including adding, updating, and searching patrons.
Loan Management:
Record loans, return books, and manage overdue items.
Graphical User Interface (GUI):
Intuitive interface for managing library operations.
Persistent Data Storage:
Data is stored and retrieved from books.txt, loans.txt, and patrons.txt.
📚 Technologies Used
Java: Core programming language.
Swing or JavaFX (GUI): For graphical user interface implementation.
JUnit: For unit testing.
🛠️ Development and Testing
The test/ folder contains unit tests to ensure system reliability.
Use JUnit (or the testing framework specified in your IDE) to run the test cases.
✨ Acknowledgments
This project was developed as part of my university coursework for CMP5332, focusing on Java programming and software design principles. Special thanks to instructors and peers for their guidance and support.
