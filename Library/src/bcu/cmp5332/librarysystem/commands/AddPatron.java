package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class AddPatron implements Command {

    private final String name;
    private final String phone;
	private final String email;


    public AddPatron(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;

    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int nextId = getNextPatronId(library);

        Patron patron = new Patron(nextId, name, phone, email);
        library.addPatron(patron);
        System.out.println("Patron #" + patron.getId() + " added.");
    }

    private int getNextPatronId(Library library) {
        Collection<Patron> allPatrons = library.getAllPatrons();
        return allPatrons.isEmpty() ? 1 : Collections.max(allPatrons.stream()
                                              .map(Patron::getId)
                                              .collect(Collectors.toList())) + 1;
    }

}