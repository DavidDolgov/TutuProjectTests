import com.codeborne.selenide.Condition;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AviaSearchFormTest extends BaseTest {
    private static final ConfigProject config = ConfigFactory.create(ConfigProject.class);
    private static final String BASE_URL = config.baseUrl();

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldReturnAllValuesFromFields(String fromWhere, String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(fromWhere);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldWhen(DataGenerator.generateForDepartureDateField());
        mainPage.setValueInFieldBack(DataGenerator.generateForArrivalDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);

        TicketSearchResultPage actual = mainPage.findTickets();

        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(fromWhere));
        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(where));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(DataGenerator.getDateForDeparture()));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(DataGenerator.getDateForArrival()));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(quantityPassengers + " пассажир"));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(economyOrBusiness ? "Эконом" : "Бизнес"));
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldReturnAllValuesWithOneDate1(String fromWhere, String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(fromWhere);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldWhen(DataGenerator.generateForDepartureDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);

        TicketSearchResultPage actual = mainPage.findTickets();

        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(fromWhere));
        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(where));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(DataGenerator.getDateForDeparture()));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(quantityPassengers + " пассажир"));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(economyOrBusiness ? "Эконом" : "Бизнес"));
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldReturnAllValuesWithOneDate2(String fromWhere, String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(fromWhere);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldBack(DataGenerator.generateForArrivalDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);

        TicketSearchResultPage actual = mainPage.findTickets();

        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(fromWhere));
        actual.getFromWhereAndWhereTo()
                .shouldHave(Condition.innerText(where));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(DataGenerator.getDateForArrival()));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(quantityPassengers + " пассажир"));
        actual.getDatesAndNumberOfPassengers()
                .shouldHave(Condition.innerText(economyOrBusiness ? "Эконом" : "Бизнес"));
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldShowErrorThatFieldWhereIsEmpty(String fromWhere) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(fromWhere);
        mainPage.setValueInFieldWhen(DataGenerator.generateForDepartureDateField());
        mainPage.setValueInFieldBack(DataGenerator.generateForArrivalDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);
        mainPage.findTickets();

        mainPage.getErrorThatFieldWhereIsEmpty().shouldBe(Condition.visible);
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldShowErrorThatFieldFromIsEmpty(String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldWhen(DataGenerator.generateForDepartureDateField());
        mainPage.setValueInFieldBack(DataGenerator.generateForArrivalDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);
        mainPage.findTickets();

        mainPage.getErrorThatFieldFromIsEmpty().shouldBe(Condition.visible);
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldShowErrorThatFieldsFromAndToAreIdentical(String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(where);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldWhen(DataGenerator.generateForDepartureDateField());
        mainPage.setValueInFieldBack(DataGenerator.generateForArrivalDateField());
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);
        mainPage.findTickets();

        mainPage.getErrorThatFieldsFromAndToAreIdentical().shouldBe(Condition.visible);
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/citiesForTesting.csv")
    public void shouldShowErrorThatDepartureDateFieldIsEmpty(String fromWhere, String where) {

        int quantityPassengers = DataGenerator.getQuantityPassengers();
        boolean economyOrBusiness = DataGenerator.getEconomyOrBusiness();

        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.setValueInFieldFromWhere(fromWhere);
        mainPage.setValueInFieldWhere(where);
        mainPage.setValueInFieldWhoIsFlying(quantityPassengers, economyOrBusiness);
        mainPage.findTickets();

        mainPage.getErrorThatDepartureDateFieldIsEmpty().shouldBe(Condition.visible);
    }
}
