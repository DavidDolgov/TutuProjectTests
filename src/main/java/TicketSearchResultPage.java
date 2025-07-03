import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class TicketSearchResultPage {
    //Данные с полей откуда и куда
    private final SelenideElement fromWhereAndWhereTo = $x("//div/span[@data-ti='route']");
    //Данные с полей когда и сколько пассажиров
    private final SelenideElement datesAndNumberOfPassengers = $x("//div/span[@data-ti='info']");
}
