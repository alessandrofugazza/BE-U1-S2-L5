package alessandrofugazza;

import alessandrofugazza.entities.*;
import com.github.javafaker.Faker;
import exceptions.InvalidActionChoiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class App
{
    public static void main( String[] args )
    {
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
        Map<Integer, WrittenWork> catalogue = new HashMap<>();

        Book newBook = bookSupplier.get();
        catalogue.put(newBook.getIsbn(), newBook);
        Magazine newMagazine = magazineSupplier.get();
        catalogue.put(newMagazine.getIsbn(), newMagazine);
        newBook = bookSupplier.get();
        catalogue.put(newBook.getIsbn(), newBook);
        newMagazine = magazineSupplier.get();
        catalogue.put(newMagazine.getIsbn(), newMagazine);
//        newBook = bookSupplier.get();
//        addWrittenWork(catalogue, newBook);

        UserActions action = null;
        do {
            System.out.println("Choose an action by typing the corresponding number and then press Enter. Insert 0 to close the app.");
            System.out.println("" +
                    "\t1. Add an item to the catalogue\n" +
                    "\t2. Remove an item from the catalogue\n" +
                    "\t3. Search by ISBN\n" +
                    "\t4. Search by year of publication\n" +
                    "\t5. Search by author\n" +
                    "\t6. Save to disk\n" +
                    "\t7. Load from disk"
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
                    action = UserActions.ADD;
                    break;
                }
                case (2): {
                    action = UserActions.REMOVE;
                    break;
                }
                case (3): {
                    action = UserActions.SEARCH_BY_ISBN;
                    break;
                }
                case (4): {
                    action = UserActions.SEARCH_BY_YEAR;
                    break;
                }
                case (5): {
                    action = UserActions.SEARCH_BY_AUTHOR;
                    break;
                }
                case (6): {
                    action = UserActions.SAVE;
                    break;
                }
                case (7): {
                    action = UserActions.LOAD;
                    break;
                }
                case (0): {
                    log.info("Thanks for using the catalogue.");
                    System.exit(0);
                    break;
                }
            }

            log.debug(String.valueOf(actionChoice));
            break;
        } while (true);

//        log.debug("done");
    }

    public static void addWrittenWork(Map<Integer, WrittenWork> catalogue, Book newBook) {
        catalogue.put(newBook.getIsbn(), newBook);
    }

}

