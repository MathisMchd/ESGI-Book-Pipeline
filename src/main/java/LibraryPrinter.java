public class LibraryPrinter {

    public String formatLibrary(Library library) {
        if (library.getBooks().isEmpty()) {
            return "La bibliothèque est vide.";
        }

        StringBuilder sb = new StringBuilder("Livres dans la bibliothèque :\n");
        for (Book b : library.getBooks()) {
            sb.append(" - ").append(b).append("\n");
        }
        return sb.toString();
    }

    public void display(Library library) {
        System.out.println(formatLibrary(library));
    }
}
