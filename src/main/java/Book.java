

public class Book {
    private String title;
    private String author;
    private int yearOfPublication;

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getyearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public String toString() {
        return "- Titre : '" + title + "' écrit par :" + author + " (" + yearOfPublication + ")";
    }
}
