package alessandrofugazza;

import alessandrofugazza.entities.Book;
import alessandrofugazza.entities.Magazine;
import alessandrofugazza.entities.Periodicity;
import alessandrofugazza.entities.WrittenWork;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class App
{
    public static void main( String[] args )
    {
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
                (short) faker.number().numberBetween(-2000, 2023),
                (short) faker.number().numberBetween(1, 1500),
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
        log.debug("done");
    }
}
