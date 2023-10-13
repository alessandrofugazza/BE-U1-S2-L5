package alessandrofugazza.entities;

public class Magazine extends Publication {
    private Periodicity periodicity;

    public Magazine(int isbn, short publicationYear, short numberOfPages, Periodicity periodicity) {
        super(isbn, publicationYear, numberOfPages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", isbn=" + isbn +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
