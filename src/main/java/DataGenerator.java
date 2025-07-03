import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final long addDaysForDepartureDateField = faker.number().numberBetween(1, 5);
    private static final long addDaysForArrivalDateField = addDaysForDepartureDateField + 5;
    private static final String patternDate = "EEE MMM dd yyyy";
    private static final String patternDateForExpected = "d";

    public static String generateForDepartureDateField() {
        return LocalDate.now()
                .plusDays(addDaysForDepartureDateField)
                .format(DateTimeFormatter.ofPattern(patternDate, Locale.ENGLISH));
    }

    public static String generateForArrivalDateField() {
        return LocalDate.now()
                .plusDays(addDaysForArrivalDateField)
                .format(DateTimeFormatter.ofPattern(patternDate, Locale.ENGLISH));
    }

    public static String getDateForDeparture() {
        return LocalDate.now()
                .plusDays(addDaysForDepartureDateField)
                .format(DateTimeFormatter.ofPattern(patternDateForExpected));
    }

    public static String getDateForArrival() {
        return LocalDate.now()
                .plusDays(addDaysForArrivalDateField)
                .format(DateTimeFormatter.ofPattern(patternDateForExpected));
    }

    public static int getQuantityPassengers() {
        return faker.number().numberBetween(1, 9);
    }

    public static boolean getEconomyOrBusiness() {
        return faker.bool().bool();
    }
}
