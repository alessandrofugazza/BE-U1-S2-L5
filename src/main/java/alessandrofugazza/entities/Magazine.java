package alessandrofugazza.entities;

public class Magazine extends WrittenWork {
    private Periodicity periodicity;

    public Magazine(int isbn, short publicationYear, short numberOfPages, Periodicity periodicity) {
        super(isbn, publicationYear, numberOfPages);
        this.periodicity = periodicity;
    }
}
