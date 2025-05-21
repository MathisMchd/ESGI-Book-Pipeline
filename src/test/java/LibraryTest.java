import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class LibraryTest {

    private Library library;
    Book book1 = new Book("Book de Téste avec accent", "Auteurs", 2010);

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() throws BookException {
        String expectedTitle = "Book 1";
        Book expected = new Book(expectedTitle, "Auteur 22", 2021);
        library.addBook(expected);

        Book actual = library.findBookByTitle("Book 1");
        assertNotNull(actual);
        assertEquals(expectedTitle, actual.getTitle());
    }


    @Test
    public void testAddBooksWithSameTitleDifferentCasesAndAccents() throws BookException {
        Book original = new Book("Café", "Auteur 1", 2020);
        Book duplicate = new Book("CAFE", "Auteur 2", 2021);

        library.addBook(original);
        assertThrows(BookException.class, () -> library.addBook(duplicate));
    }


    @Test
    public void testAddBookAlreadyAddedThrowsBookException() throws BookException {
        library.addBook(book1);
        assertThrows(BookException.class, () -> library.addBook(book1));
    }

    @Test
    public void testFindBookByTitleWithAccentAndMaj() throws BookException {
        Book book = new Book("Établissement", "Auteur", 1999);
        library.addBook(book);

        Book res1 = library.findBookByTitle("établissement");
        Book res2 = library.findBookByTitle("eTablissement");
        Book res3= library.findBookByTitle("étabLisseMent");
        assertNotNull(res1);
        assertNotNull(res2);
        assertNotNull(res3);
        assertEquals(book, res1);
        assertEquals(book, res2);
        assertEquals(book, res3);
    }

    @Test
    public void testRemoveBookByTitle() throws BookException {
        Book book = new Book("Les Invalides", "Auteurs inconnu", 1200);
        library.addBook(book);

        boolean removed = library.removeBookByTitle("Les Invalides");
        assertTrue(removed);
        assertNull(library.findBookByTitle("Les Invalides"));
    }

    @Test
    public void testRemoveBookByTitleWhereBookNotFound() {
        boolean removed = library.removeBookByTitle("Titre inconnu");
        assertFalse(removed);
    }

    @Test
    public void testFindBookByTitleNotFound() {
        Book result = library.findBookByTitle("Titre Inexistant");
        assertNull(result);
    }



    // Affichage de la library avec une classe LibraryPrinter pour externaliser la logique
    // Normalement il faudrait créer les services avec interfaces, et créer un nouveau fichiers de test
    @Test
    public void testFormatLibraryWithBooks() throws BookException {
        Library lib = new Library();
        LibraryPrinter printer = new LibraryPrinter();
        lib.addBook(new Book("BOok 1", "Auteur", 2023));

        String output = printer.formatLibrary(lib);
        assertTrue(output.contains("Livres dans la bibliothèque"));
        assertTrue(output.contains("BOok 1"));
    }






    @Test
    void testGetAllBooksSortedByTitle() throws BookException {
        Book book1 = new Book("Zyx", "Auteur X", 2001);
        Book book2 = new Book("Beba", "Auteur Y", 2011);
        Book book3 = new Book("Ata", "Auteur Z", 2020);
        Book book4 = new Book("With", "Auteur hdj", 2011);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        List<Book> sortedBooks = library.getAllBooksSortedByTitle();
        assertEquals(4, sortedBooks.size());
        assertEquals("Ata", sortedBooks.get(0).getTitle());
        assertEquals("Beba", sortedBooks.get(1).getTitle());
        assertEquals("With", sortedBooks.get(2).getTitle());
        assertEquals("Zyx", sortedBooks.get(3).getTitle());
    }


    @Test
    void testFindBooksByPartialTitleWithExactTitle() throws BookException {
        Book book1 = new Book("Le timbre", "Auteur X", 1999);
        Book book2 = new Book("Le timbre et tuiles", "Auteur Y", 2005);
        Book book3 = new Book("A l'ombre", "Auteur Z", 2010);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        List<Book> results = library.findBooksByPartialTitle("Le timbre");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(b -> b.getTitle().equals(book1.getTitle())));
        assertTrue(results.stream().anyMatch(b -> b.getTitle().equals(book2.getTitle())));
    }

    @Test
    void testFindBooksByPartialTitle() throws BookException {
        Book book1 = new Book("Le timbre", "Auteur X", 1999);
        Book book2 = new Book("Les tuiles timbre et orage", "Auteur Y", 2005);
        Book book3 = new Book("A l'ombre", "Auteur Z", 2010);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        List<Book> results = library.findBooksByPartialTitle("timbre");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(b -> b.getTitle().equals(book1.getTitle())));
        assertTrue(results.stream().anyMatch(b -> b.getTitle().equals(book2.getTitle())));
    }

    @Test
    void testFindBooksByPartialTitleCaseInsensitive() throws BookException {
        Book book = new Book("Montagne Sacrée", "Auteur A", 2012);
        library.addBook(book);

        List<Book> results = library.findBooksByPartialTitle("montagne");
        assertEquals(1, results.size());
        assertEquals(book, results.get(0));

        results = library.findBooksByPartialTitle("MONTAGNE");
        assertEquals(1, results.size());
        assertEquals(book, results.get(0));
    }

    @Test
    void testFindBooksByPartialTitle_accentInsensitive() throws BookException {
        Book book = new Book("Étoile Filante", "Auteur B", 2018);
        library.addBook(book);

        List<Book> results = library.findBooksByPartialTitle("etoile");
        assertEquals(1, results.size());
        assertEquals(book, results.get(0));

        results = library.findBooksByPartialTitle("Étoile");
        assertEquals(1, results.size());
        assertEquals(book, results.get(0));
    }

    @Test
    void testFindBooksByPartialTitle_noMatch() throws BookException {
        Book book = new Book("Le vieux chêne", "Auteur C", 1995);
        library.addBook(book);

        List<Book> results = library.findBooksByPartialTitle("rivière");
        assertTrue(results.isEmpty());
    }
}