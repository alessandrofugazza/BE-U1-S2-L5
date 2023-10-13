package alessandrofugazza.entities;

public class Book extends Publication {
    private String author;
    private String genre;

    public Book(int isbn, short publicationYear, short numberOfPages, String author, String genre) {
        super(isbn, publicationYear, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }


    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbn=" + isbn +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
