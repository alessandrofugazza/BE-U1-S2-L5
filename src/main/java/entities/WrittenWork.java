package entities;

public abstract class WrittenWork {
    protected int isbn;
    protected short publicationYear;
    protected short numberOfPages;

    public WrittenWork(int isbn, short publicationYear, short numberOfPages) {
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }
}
