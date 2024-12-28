package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryData {

	private static final List<DataManager> dataManagers = new ArrayList<>();

	static {
		// Initialize data managers for different data types in the library system.
		dataManagers.add(new BookDataManager());   // Manages book data
		dataManagers.add(new PatronDataManager()); // Manages patron data
		dataManagers.add(new LoanDataManager());   // Manages loan data
	}


	public static Library load() throws LibraryException, IOException {

		Library library = new Library();
		for (DataManager dm : dataManagers) {
			dm.loadData(library);
		}
		return library;
	}

	public static void store(Library library) throws IOException {

		for (DataManager dm : dataManagers) {
			dm.storeData(library);
		}
	}

}
