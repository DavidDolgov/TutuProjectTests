import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.$x;

//вся логика с элементами на главноя страницы tutu.ru
@Getter
public class MainPage {
    //все поля для ввода информации
    private final SelenideElement inputFieldFromWhere = $x("//div//span[contains(text(),'Откуда')]/../input[@placeholder=' ']");
    private final SelenideElement inputFieldWhere = $x("//div//span[contains(text(),'Куда')]/../input[@placeholder=' ']");
    private final SelenideElement inputFieldWhen = $x("//div//span[contains(text(),'Когда')]/../input[@placeholder=' ']");
    private final SelenideElement inputFieldBack = $x("//div//span[contains(text(),'Обратно')]/../input[@placeholder=' ']");
    private final SelenideElement inputFieldWhoIsFlying = $x("//div//span[contains(text(),'Кто летит')]/../input[@placeholder=' ']");

    //кнопки отчиски и принятия относительно даты
    private final SelenideElement selectButton = $x("//button[@variant='primary']");

    //кнопки из блока с количеством пасажиров
    private final SelenideElement economyButton = $x("//div[@data-ti='panel']/button[contains(text(),'Эконом')]");
    private final SelenideElement businessButton = $x("//div[@data-ti='panel']/button[contains(text(),'Бизнес')]");
    private final SelenideElement plusPassengerButton = $x("//button[@data-ti='plus_button']");

    //кнопка найти билеты
    private final SelenideElement submitButton = $x("//button[@data-ti='submit-button']");

    //Элементы по ошибкам заполнения формы
    private final SelenideElement errorThatFieldWhereIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите город прилёта')]");
    private final SelenideElement errorThatFieldFromIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите город вылета')]");
    private final SelenideElement errorThatFieldsFromAndToAreIdentical = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'должны совпадать')]");
    private final SelenideElement errorThatDepartureDateFieldIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите дату вылета')]");


    public MainPage(String baseUrl) {
        Selenide.open(baseUrl);
    }

    public void setValueInFieldFromWhere(String text) {
        inputFieldFromWhere.sendKeys(text);
        $x("//div[@data-ti='dropdown-item']//*[contains(text(),'" + text + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldWhere(String text) {
        inputFieldWhere.sendKeys(text);
        $x("//div[@data-ti='dropdown-item']//*[contains(text(),'" + text + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldWhen(String data) {
        inputFieldWhen.click();
        $x("//div[@class='DayPicker-Day'] [contains(@aria-label,'" + data + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldBack(String data) {
        inputFieldBack.click();
        $x("//div[@class='DayPicker-Day'] [contains(@aria-label,'" + data + "')]")
                .shouldBe(Condition.visible).click();
        selectButton.click();
    }

    public void setValueInFieldWhoIsFlying(int adultPassengers, boolean economy) {
        inputFieldWhoIsFlying.click();
        SelenideElement economyOrBusiness = economy ? economyButton : businessButton;
        economyOrBusiness.shouldBe(Condition.visible).click();

        IntStream stream = IntStream.range(1,adultPassengers);
        stream.forEach(i -> plusPassengerButton.click());
    }

    public TicketSearchResultPage findTickets() {
        submitButton.click();
        return new TicketSearchResultPage();
    }
}
