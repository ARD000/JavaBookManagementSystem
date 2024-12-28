package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class DeletePatronCommand implements Command {
    private final int patronId;

    public DeletePatronCommand(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        library.deletePatron(patronId);
        System.out.println("Patron with ID: " + patronId + " has been successfully deleted.");
    }
}

