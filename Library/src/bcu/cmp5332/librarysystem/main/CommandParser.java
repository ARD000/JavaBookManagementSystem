package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.commands.LoadGUI; 
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.commands.ListBooks;
import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.DeleteBookCommand;
import bcu.cmp5332.librarysystem.commands.Help;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CommandParser {

	public static Command parse(String line) throws IOException, LibraryException {
		try {
			String[] parts = line.split(" ", 3);
			String cmd = parts[0];

			if (cmd.equals("addbook")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Title: ");
				String title = br.readLine();
				System.out.print("Author: ");
				String author = br.readLine();
				System.out.print("Publication Year: ");
				String publicationYear = br.readLine();
				System.out.print("Publisher: ");
				String publisher = br.readLine(); 

				return new AddBook(title, author, publicationYear, publisher);
			} else if (cmd.equals("addpatron")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Name: ");
				String name = br.readLine();
				System.out.print("Phone: ");
				String phone = br.readLine();
				System.out.print("Email: ");
				String email = br.readLine();

				return new AddPatron(name, phone, email);
			} else if (cmd.equals("loadgui")) {
				return new LoadGUI();
			} else if (parts.length == 1) {
				if (line.equals("listbooks")) {
					return new ListBooks();
				} else if (line.equals("listpatrons")) {
					//This will list all patrons.
					return listPatronsCommand();
				} else if (line.equals("help")) {
					return new Help();
				}
			} else if (parts.length == 2) {
				int id = Integer.parseInt(parts[1]);

				if (cmd.equals("showbook")) {
					// Showing a book using the ID as parameter
					return showBookCommand(id);
				} else if (cmd.equals("showpatron")) {
					// Showing a patron using the ID as parameter
					return showPatronCommand(id);
				} else if (cmd.equals("deletepatron")) {
					// Delete a patron using the ID as parameter
					return deletePatronCommand(id);
				}
				else if (cmd.equals("deletebook")) {
					int bookId = Integer.parseInt(parts[1]);
					return new DeleteBookCommand(bookId);
				}
			} else if (parts.length == 3) {
				int patronID = Integer.parseInt(parts[1]);
				int bookID = Integer.parseInt(parts[2]);

				if (cmd.equals("borrow")) {
					return borrowBookCommand(patronID, bookID);
				} else if (cmd.equals("renew")) {
					return renewBookCommand(patronID, bookID);
				} else if (cmd.equals("return")) {
					return returnBookCommand(patronID, bookID);
				}
			}
		} catch (NumberFormatException ex) {

		}

		throw new LibraryException("Invalid command.");
	}

	// Command executions below. 

	private static Command showBookCommand(int bookId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				Book book = library.getBookByID(bookId);
				System.out.println(book.getDetailsLong());
				if (book.isOnLoan()) {
					Loan loan = book.getLoan();
					String borrowerDetails = loan.getBorrowerDetails();
					System.out.println("Borrowed by: " + borrowerDetails);
				} else {
					System.out.println("The book is currently available.");
				}
			}
		};
	}


	private static Command showPatronCommand(int patronId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				Patron patron = library.getPatronByID(patronId);
				System.out.println("Patron ID: " + patron.getId());
				System.out.println("Name: " + patron.getName());
				System.out.println("Phone: " + patron.getPhone());

				List<Book> borrowedBooks = patron.getBooks();
				if (borrowedBooks.isEmpty()) {
					System.out.println(patron.getName() + " has not borrowed any books.");
				} else {
					System.out.println("Books borrowed by " + patron.getName() + ":");
					for (Book book : borrowedBooks) {
						System.out.println("- " + book.getDetailsShort());
					}
				}
			}
		};
	}

	private static Command borrowBookCommand(int patronId, int bookId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				Book book = library.getBookByID(bookId);
				Patron patron = library.getPatronByID(patronId);
				LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());

				patron.borrowBook(book, dueDate);

				// This is where a new Loan object is created which is then set to the book
				Loan loan = new Loan(patron, book, currentDate, dueDate);
				book.setLoan(loan);

				library.addLoan(loan); 

				System.out.println(book.getTitle() + " has been successfully borrowed by " +
						patron.getName() + " (ID: " + patron.getId() + ").");
			}
		};
	}


	private static Command renewBookCommand(int patronId, int bookId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the number of additional days for renewing the book:");

				int additionalDays;
				try {
					additionalDays = Integer.parseInt(scanner.nextLine());
					if (additionalDays < 0) {
						throw new LibraryException("Number of additional days cannot be negative.");
					}
				} catch (NumberFormatException e) {
					throw new LibraryException("Invalid input. Please enter a valid number.");
				}

				Book book = library.getBookByID(bookId);
				Patron patron = library.getPatronByID(patronId);

				LocalDate newDueDate = currentDate.plusDays(additionalDays);

				patron.renewBook(book, newDueDate);

				if (book.isOnLoan()) {
					Loan loan = book.getLoan();
					loan.setDueDate(newDueDate);
				}

				System.out.println("An additional " + additionalDays + " days for '" + book.getTitle() + 
						"' has been set to be borrowed by " + patron.getName() + 
						" (ID: " + patron.getId() + ").");
			}
		};
	}



	private static Command listPatronsCommand() {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				for (Patron patron : library.getAllPatrons()) {
					System.out.println("Patron ID: " + patron.getId());
					System.out.println("Name: " + patron.getName());
					System.out.println("Phone: " + patron.getPhone());
					System.out.println("Email: " + patron.getEmail());


					List<Book> borrowedBooks = patron.getBooks();
					if (borrowedBooks.isEmpty()) {
						System.out.println("No books currently borrowed.");
					} else {
						System.out.println("Borrowed Books:");
						for (Book book : borrowedBooks) {
							System.out.println("\tBook ID: " + book.getId() + ", Title: " + book.getTitle());
						}
					}
				}
			}
		};
	}


	private static Command returnBookCommand(int patronId, int bookId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				Book book = library.getBookByID(bookId);
				Patron patron = library.getPatronByID(patronId);

				patron.returnBook(book);

				library.removeLoan(book.getLoan()); 

				System.out.println("'" + book.getTitle() + "' has been successfully returned by " + 
						patron.getName() + " (ID: " + patron.getId() + ").");
			}
		};
	}

	private static Command deletePatronCommand(int patronId) {
		return new Command() {

			@Override
			public void execute(Library library, LocalDate currentDate) throws LibraryException {
				library.deletePatron(patronId);
			}
		};
	}





}