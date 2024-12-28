package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/loans.txt";
    private static final String SEPARATOR = ",";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        if (!Files.exists(Paths.get(RESOURCE))) {
            return;
        }

        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                
                int bookId = Integer.parseInt(properties[0]);
                int patronId = Integer.parseInt(properties[1]);
                LocalDate startDate = LocalDate.parse(properties[2]);
                LocalDate dueDate = LocalDate.parse(properties[3]);

                Book book = library.getBookByID(bookId);
                Patron patron = library.getPatronByID(patronId);
                Loan loan = new Loan(patron, book, startDate, dueDate);
                book.setLoan(loan);
                patron.addBook(book);
            }
        } catch (Exception ex) {
            throw new LibraryException("Error loading loan data: " + ex.getMessage());
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Book book : library.getBooks()) {
                Loan loan = book.getLoan();
                if (loan != null) {
                    out.print(book.getId() + SEPARATOR);
                    out.print(loan.getPatron().getId() + SEPARATOR);
                    out.print(loan.getStartDate() + SEPARATOR);
                    out.print(loan.getDueDate());
                    out.println();
                }
            }
        }
    }
}