package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library; 
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PatronDataManager implements DataManager {

	private static final String SEPARATOR = "::"; 
	private final String RESOURCE = "./resources/data/patrons.txt";

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		try (Scanner sc = new Scanner(new File(RESOURCE))) {
			int lineIdx = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] properties = line.split(SEPARATOR, -1);
				try {
					int id = Integer.parseInt(properties[0]);
					String name = properties[1];
					String phone = properties[2];
					String email = properties[3];
					Patron patron = new Patron(id, name, phone, email);
					library.addPatron(patron);
				} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
					throw new LibraryException("Error parsing patron data on line " + lineIdx + ": " + ex.getMessage());
				}
				lineIdx++;
			}
		}    
	}

	@Override
	public void storeData(Library library) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
			for (Patron patron : library.getAllPatrons()) { 
				out.print(patron.getId() + SEPARATOR);
				out.print(patron.getName() + SEPARATOR);
				out.print(patron.getPhone() + SEPARATOR);
				out.print(patron.getEmail() + SEPARATOR);
				out.println();
			}
		}
	}
}

