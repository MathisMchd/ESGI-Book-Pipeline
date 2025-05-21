import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryFunctionalTest {


    // Pareil ici on a la dépendance a LibraryPrinter
    // qu'il faudrait externaliser mais cela test correctement la sortie

    private Library library = new Library();
    private LibraryPrinter printer = new LibraryPrinter();


    @Test
    public void testFullScenario() throws BookException {
        // Ajout de plusieurs livres
        Book book1 = new Book("1984", "George Orwell", 1949);
        Book book2 = new Book("Dune", "Frank Herbert", 1965);
        Book book3 = new Book("Livre Trois", "Auteur Y", 2010);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Vérifier que les livres ont été ajoutés
        assertNotNull(library.findBookByTitle("1984"));
        assertNotNull(library.findBookByTitle("Dune"));
        assertNotNull(library.findBookByTitle("LIVRE TROIS")); // on test le case sensitive

        // Suppression d'un livre
        boolean removed = library.removeBookByTitle("Dune");
        assertTrue(removed);

        // Vérifier que le livre a bien été supprimé
        assertNull(library.findBookByTitle("Dune"));

        // Affichage de la bibliothèque restante
        library.displayBooks();

        String output = printer.formatLibrary(library);

        // Vérifie que la sortie contient les bons livres (sans "Livre Deux")
        assertTrue(output.contains("1984"));
        assertTrue(output.contains("George Orwell"));
        assertTrue(output.contains("1949"));
        assertTrue(output.contains("Livre Trois"));
        assertTrue(output.contains("Auteur Y"));
        assertTrue(output.contains("2010"));
        assertFalse(output.contains("Dune"));
        assertFalse(output.contains("Frank Herbert"));
        assertFalse(output.contains("1965"));
    }
}
