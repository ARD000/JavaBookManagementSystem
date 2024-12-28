package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.*;

public class Library {

	private final int loanPeriod = 7;
	private final Map<Integer, Patron> patrons = new TreeMap<>();
	private final Map<Integer, Book> books = new TreeMap<>();
	private final List<Loan> loans = new ArrayList<>();


	public void addLoan(Loan loan) {
		loans.add(loan);
	}

	public void removeLoan(Loan loanToRemove) {
		loans.remove(loanToRemove);
	}

	public List<Loan> getLoansByPatron(Patron patron) {
		List<Loan> patronLoans = new ArrayList<>();
		for (Loan loan : loans) {
			if (loan.getPatron().getId() == patron.getId()) {
				patronLoans.add(loan);
			}
		}
		return patronLoans;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public List<Book> getBooks() {
		List<Book> out = new ArrayList<>(books.values());
		return Collections.unmodifiableList(out);
	}

	public Book getBookByID(int id) throws LibraryException {
		if (!books.containsKey(id)) {
			throw new LibraryException("There is no such book with that ID.");
		}
		return books.get(id);
	}

	public Patron getPatronByID(int id) throws LibraryException {
		// This method will retrieve Patron from patrons using ID provided. 
		// If ID doesn't exist throw error handling exception 
		if (!patrons.containsKey(id)) {
			throw new LibraryException ("No patron found with that id");
		} 
		return patrons.get(id);
	}

	public void addBook(Book book) {
		if (books.containsKey(book.getId())) {
			throw new IllegalArgumentException("Duplicate book ID.");
		}
		books.put(book.getId(), book);
	}

	public void addPatron(Patron patron) {
		// This method will add a new patron to patrons using the Id(the patron map)
		// If patron with same Id already exists in patrons then it will throw an illegalArgumentException to prevent adding of patrons with same Id.
		if (patrons.containsKey(patron.getId())) {
			throw new IllegalArgumentException("Duplicate patron ID.");
		}
		patrons.put(patron.getId(), patron);
	}

	public Collection<Patron> getAllPatrons() {
		return new ArrayList<>(patrons.values());
	}
	
	public void deletePatron(int patronId) throws LibraryException {
        if (!patrons.containsKey(patronId)) {
            throw new LibraryException("No patron found with ID: " + patronId);
        }

        Patron patron = patrons.get(patronId);
        List<Loan> patronLoans = getLoansByPatron(patron);
        if (!patronLoans.isEmpty()) {
            throw new LibraryException("Patron has outstanding loans and cannot be deleted.");
        }

        patrons.remove(patronId);
    }
	
	public void deleteBook(int bookId) throws LibraryException {
	    if (!books.containsKey(bookId)) {
	        throw new LibraryException("No book found with ID: " + bookId);
	    }

	    Book book = books.get(bookId);

	    if (book.isOnLoan()) {
	        throw new LibraryException("Book is on loan and cannot be deleted.");
	    }

	    books.remove(bookId);
	}




}

