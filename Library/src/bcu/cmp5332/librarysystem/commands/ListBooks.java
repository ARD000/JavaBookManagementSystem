package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            // Using isOnLoan() method from book class to check if the book is on loan
            String status = book.isOnLoan() ? "Borrowed" : "Available to be borrowed";
            System.out.println(book.getDetailsShort() + " - " + status);
        }
        System.out.println(books.size() + " book(s)");
    }
}

 