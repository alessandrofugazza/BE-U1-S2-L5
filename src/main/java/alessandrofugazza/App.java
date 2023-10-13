package alessandrofugazza;

import alessandrofugazza.entities.*;
import com.github.javafaker.Faker;
import alessandrofugazza.exceptions.EmptyStringException;
import alessandrofugazza.exceptions.InvalidActionChoiceException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class App
{
    static Map<Integer, Publication> catalogue = new HashMap<>();
    public static void main( String[] args ) throws IOException {
        Scanner input = new Scanner(System.in);
        Logger log = LoggerFactory.getLogger(App.class);
        Faker faker = new Faker();
        Supplier<Book> bookSupplier= () -> new Book(
                faker.number().numberBetween(1, 10000000),
                (short) faker.number().numberBetween(-2000, 2023),
                (short) faker.number().numberBetween(1, 1500),
                faker.name().fullName(),
                faker.book().genre()
        );
        Supplier<Magazine> magazineSupplier= () -> new Magazine(
                faker.number().numberBetween(1, 10000000),
                (short) faker.number().numberBetween(1900, 2023),
                (short) faker.number().numberBetween(1, 100),
                Periodicity.values()[faker.number().numberBetween(1, Periodicity.values().length)]
        );


//        Book newBook = bookSupplier.get();
//        catalogue.put(newBook.getIsbn(), newBook);
//        Magazine newMagazine = magazineSupplier.get();
//        catalogue.put(newMagazine.getIsbn(), newMagazine);
//        newBook = bookSupplier.get();
//        catalogue.put(newBook.getIsbn(), newBook);
//        newMagazine = magazineSupplier.get();
//        catalogue.put(newMagazine.getIsbn(), newMagazine);
//        newBook = bookSupplier.get();
//        addPublication(catalogue, newBook);

        UserActions action = null;
        do {
            System.out.println("Choose an action by typing the corresponding number and then press Enter. Insert 0 to close the app.");
            System.out.println("" +
                    "\t1. Add a book to the catalogue\n" +
                    "\t2. Add a magazine to the catalogue\n" +
                    "\t3. Remove an item from the catalogue\n" +
                    "\t4. Search by ISBN\n" +
                    "\t5. Search by year of publication\n" +
                    "\t6. Search by author\n" +
                    "\t7. Save to disk\n" +
                    "\t8. Load from disk"
            );
            byte actionChoice;
            do {
                try {
                    actionChoice = Byte.parseByte(input.nextLine());
                    if (actionChoice > UserActions.values().length || actionChoice < 0) {
                        throw new InvalidActionChoiceException(actionChoice);
                    }
                    break;
                } catch (InvalidActionChoiceException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Please insert a number.");
                }
            } while (true);
            switch (actionChoice) {
                case (1): {
                    action = UserActions.ADD_BOOK;
                    break;
                }
                case (2): {
                    action = UserActions.ADD_MAGAZINE;
                    break;
                }
                case (3): {
                    action = UserActions.REMOVE;
                    break;
                }
                case (4): {
                    action = UserActions.SEARCH_BY_ISBN;
                    break;
                }
                case (5): {
                    action = UserActions.SEARCH_BY_YEAR;
                    break;
                }
                case (6): {
                    action = UserActions.SEARCH_BY_AUTHOR;
                    break;
                }
                case (7): {
                    action = UserActions.SAVE;
                    break;
                }
                case (8): {
                    action = UserActions.LOAD;
                    break;
                }
                case (0): {
                    System.out.println("Thanks for using the catalogue.");
                    input.close();
                    System.exit(0);
                    break;
                }
            }
            switch (action) {
                case ADD_BOOK: {
                    int isbn;
                    short publicationYear;
                    short numberOfPages;
                    String author;
                    String genre;
                    do {
                        try {
                            System.out.println("Insert the ISBN");
                            isbn = Integer.parseInt(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number.");
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the year of publication");
                            publicationYear = Short.parseShort(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number.");
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the number of pages");
                            numberOfPages = Short.parseShort(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number.");
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the author");
                            author = input.nextLine();
                            if (author.isEmpty()) {
                                throw new EmptyStringException("Author");
                            }
                            break;
                        } catch (EmptyStringException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the genre");
                            genre = input.nextLine();
                            if (genre.isEmpty()) {
                                throw new EmptyStringException("Genre");
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);
                    Book newBook = new Book(isbn, publicationYear, numberOfPages, author, genre);
                    addPublication(newBook);
                    break;
                }
                case ADD_MAGAZINE: {
                    int isbn;
                    short publicationYear;
                    short numberOfPages;
                    Periodicity periodicity = null;
                    do {
                        try {
                            System.out.println("Insert the ISBN");
                            isbn = Integer.parseInt(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the year of publication");
                            publicationYear = Short.parseShort(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    do {
                        try {
                            System.out.println("Insert the number of pages");
                            numberOfPages = Short.parseShort(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    do {
                        byte periodicityChoice;
                        do {
                            try {
                                System.out.println("Select the periodicity");
                                System.out.println("Available formats:\n\t1. Weekly\n\t2. Monthly\n\t3. Biannually");
                                periodicityChoice = Byte.parseByte(input.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Please insert a number.");
                            }
                        } while (true);

                        switch (periodicityChoice) {
                            case (1): {
                                periodicity = Periodicity.SETTIMANALE;
                                break;
                            }
                            case (2): {
                                periodicity = Periodicity.MENSILE;
                                break;
                            }
                            case (3): {
                                periodicity = Periodicity.SEMESTRALE;
                                break;
                            }
                            default: {
                                System.out.println("Invalid periodicity selection.");
                            }
                        }
                    } while (periodicity == null);
                    Magazine newMagazine = new Magazine(isbn, publicationYear, numberOfPages, periodicity);
                    addPublication(newMagazine);
                    break;
                }
                case REMOVE: {
                    int isbn;
                    do {
                        try {
                            System.out.println("Insert the ISBN");
                            isbn = Integer.parseInt(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    removePublication(isbn);
                    break;
                }
                case SEARCH_BY_ISBN: {
                    int isbn;
                    do {
                        try {
                            System.out.println("Insert the ISBN");
                            isbn = Integer.parseInt(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    List <Publication> results = searchByIsbn(isbn);
                    if (!results.isEmpty()) {
                        results.forEach(System.out::println);
                    } else {
                        System.out.println("No publications found for ISBN " + isbn);
                    }
                    break;
                }
                case SEARCH_BY_YEAR: {
                    short publicationYear;
                    do {
                        try {
                            System.out.println("Insert the year of publication");
                            publicationYear = Short.parseShort(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please insert a number");
                        }
                    } while (true);
                    List <Publication> results = searchByYear(publicationYear);
                    if (!results.isEmpty()) {
                        results.forEach(System.out::println);
                    } else {
                        System.out.println("No publications found for year " + publicationYear);
                    }
                    break;
                }
                case SEARCH_BY_AUTHOR: {
                    String author;
                    do {
                        try {
                            System.out.println("Insert the author");
                            author = input.nextLine();
                            if (author.isEmpty()) {
                                throw new EmptyStringException("Author");
                            }
                            break;
                        } catch (EmptyStringException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);
                    List <Publication> results = searchByAuthor(author);
                    if (!results.isEmpty()) {
                        results.forEach(System.out::println);
                    } else {
                        System.out.println("No publications found for author " + author);
                    }
                    break;
                }
                case SAVE: {
                    saveToDisk();
                    break;
                }
                case LOAD: {
                    loadFromDisk();
                    break;
                }
            }
//            catalogue.values().forEach(System.out::println);
//            log.debug(String.valueOf(actionChoice));
        } while (true);

//        log.debug("done");
    }

    public static void addPublication(Publication newPublication) {
        catalogue.put(newPublication.getIsbn(), newPublication);
    }
    public static void removePublication(int isbn) {
        Publication removedValue = catalogue.remove(isbn);
        if (removedValue != null) {
            System.out.println("Removed publication for ISBN " + isbn);
        } else {
            System.out.println("No publication found for ISBN " + isbn);
        }
    }
    public static List<Publication> searchByIsbn(int query) {
         return catalogue.values().stream().filter(publication -> publication.getIsbn() == query).toList();
    }
    public static List<Publication> searchByYear(short query) {
         return catalogue.values().stream().filter(publication -> publication.getPublicationYear() == query).toList();
    }
    public static List<Publication> searchByAuthor(String query) {
         return catalogue.values().stream().filter(publication -> publication instanceof Book).filter(book -> ((Book) book).getAuthor().equals(query)).toList();
    }
    public static void saveToDisk() throws IOException {
        String toWrite = "";

        for (Publication publication : catalogue.values()) {
            toWrite += publication.getIsbn() + "@" + publication.getPublicationYear() + "@" + publication.getNumberOfPages() + "@";
            if (publication instanceof Book) {
                toWrite += ((Book) publication).getAuthor() + "@" + ((Book) publication).getGenre() + "@BOOK#";
            } else if (publication instanceof Magazine) {
                toWrite += ((Magazine) publication).getPeriodicity() + "@MAGAZINE#";
            }
        }

        File f = new File("catalogue.txt");
        FileUtils.writeStringToFile(f, toWrite, "UTF-8");
        System.out.println("Catalogue saved correctly.");
    }
    public static void loadFromDisk() throws IOException {
        File f = new File("catalogue.txt");

        String fileString = FileUtils.readFileToString(f, "UTF-8");
        if (!fileString.isEmpty()) {
            List<String> splitElementsString = Arrays.asList(fileString.split("#"));

            splitElementsString.forEach(string -> {
//            Publication publication = null;
                String[] publicationInfo = string.split("@");
                int isbn = Integer.parseInt(publicationInfo[0]);
                short publicationYear = Short.parseShort(publicationInfo[1]);
                short numberOfPages = Short.parseShort(publicationInfo[2]);
                if (publicationInfo[publicationInfo.length-1].equals("BOOK")) {
                    String author = publicationInfo[3];
                    String genre = publicationInfo[4];
                    catalogue.put(isbn, new Book(isbn, publicationYear, numberOfPages, author, genre));
//                publication = new Publication(isbn, new Book(isbn, publicationYear, numberOfPages, author, genre));
                } else if (publicationInfo[publicationInfo.length-1].equals("MAGAZINE")) {
                    Periodicity periodicity = Periodicity.valueOf(publicationInfo[3]);
                    catalogue.put(isbn, new Magazine(isbn, publicationYear, numberOfPages, periodicity));
//                publication = new Magazine(isbn, publicationYear, numberOfPages, periodicity);
                }
//            return publication;
            });
//        }).toList();
            System.out.println("Catalogue loaded.");
        } else {
            System.out.println("The catalogue was empty.");
        }

    }
}

