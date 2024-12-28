package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class DeleteBookCommand implements Command {
    private final int bookId;

    public DeleteBookCommand(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        library.deleteBook(bookId);
        System.out.println("Book with ID: " + bookId + " has been successfully deleted.");
    }
}
