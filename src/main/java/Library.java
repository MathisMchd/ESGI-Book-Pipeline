import java.util.*;
import java.util.stream.Collectors;

public class Library {

    // La clé est le titre, qui doit donc être unique
    // Je le met en minuscule pour éviter les problèmes
    private Map<String, Book> books = new HashMap<>();

    // Ajoute un livre ou throw une exception si le meme titre existe deja
    public void addBook(Book book) throws BookException {
        if (books.containsKey(Utils.normalize(book.getTitle()))) {
            throw new BookException("Un livre avec ce titre existe déjà.");
        }
        books.put(Utils.normalize(book.getTitle()), book);
    }

    // Supprime un livre par son titre
    public boolean removeBookByTitle(String title) {
        return books.remove(Utils.normalize(title)) != null;
    }

    // Recherche un titre par son titre
    public Book findBookByTitle(String title) {
        return books.get(Utils.normalize(title));
    }

    // Récupère tous les livres
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    // Affichage des livres dans la console, sinon la classe LibraryPrinter
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("La bibliothèque est vide.");
        } else {
            System.out.println("Livres dans la bibliothèque :");
            for (Book b : books.values()) {
                System.out.println(" - " + b + "\n");
            }
        }
    }


    public List<Book> getAllBooksSortedByTitle() {
        List<Book> sortedBooks = new ArrayList<>(books.values());
        sortedBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return sortedBooks;
    }

    public List<Book> findBooksByPartialTitle(String partialTitle) {
        String normalizedPartial = Utils.normalize(partialTitle);
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            String normalizedTitle = Utils.normalize(book.getTitle());
            if (normalizedTitle.contains(normalizedPartial)) {
                result.add(book);
            }
        }
        return result;
    }


}
