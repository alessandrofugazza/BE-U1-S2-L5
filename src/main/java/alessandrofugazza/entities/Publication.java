package alessandrofugazza.entities;

public abstract class Publication {
    protected int isbn;
    protected short publicationYear;
    protected short numberOfPages;

    public Publication(int isbn, short publicationYear, short numberOfPages) {
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

    public int getIsbn() {
        return isbn;
    }

    public short getPublicationYear() {
        return publicationYear;
    }

    public short getNumberOfPages() {
        return numberOfPages;
    }
}
