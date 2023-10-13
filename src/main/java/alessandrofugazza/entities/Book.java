package alessandrofugazza.entities;

public class Book extends WrittenWork {
    private String author;
    private String genre;

    public Book(int isbn, short publicationYear, short numberOfPages, String author, String genre) {
        super(isbn, publicationYear, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

}
