import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;


import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

//вся логика с элементами на главноя страницы tutu.ru
public class MainPage {
    //все поля для ввода информации
    private final ElementsCollection inputFields = $$x("//input[@placeholder=' ']");

    //кнопки отчиски и принятия относительно даты
    private final SelenideElement selectButton = $x("//button[@variant='primary']");

    //кнопки из блока с количеством пасажиров
    private final SelenideElement economyButton = $x("//div[@data-ti='panel']/button[contains(text(),'Эконом')]");
    private final SelenideElement businessButton = $x("//div[@data-ti='panel']/button[contains(text(),'Бизнес')]");
    private final SelenideElement plusPassengerButton = $x("//button[@data-ti='plus_button']");

    //кнопка найти билеты
    private final SelenideElement submitButton = $x("//button[@data-ti='submit-button']");

    //Элементы по ошибкам заполнения формы
    @Getter
    private final SelenideElement errorThatFieldWhereIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите город прилёта')]");
    @Getter
    private final SelenideElement errorThatFieldFromIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите город вылета')]");
    @Getter
    private final SelenideElement errorThatFieldsFromAndToAreIdentical = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'должны совпадать')]");
    @Getter
    private final SelenideElement errorThatDepartureDateFieldIsEmpty = $x("//div[@data-ti='tooltip-content']/span[contains(text(),'Выберите дату вылета')]");


    public MainPage(String baseUrl) {
        Selenide.open(baseUrl);
    }

    public void setValueInFieldFromWhere(String text) {
        inputFields.get(0).sendKeys(text);
        $x("//div[@data-ti='dropdown-item']//*[contains(text(),'" + text + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldWhere(String text) {
        inputFields.get(1).setValue(text);
        $x("//div[@data-ti='dropdown-item']//*[contains(text(),'" + text + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldWhen(String data) {
        inputFields.get(2).click();
        $x("//div[@class='DayPicker-Day'] [contains(@aria-label,'" + data + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void setValueInFieldBack(String data) {
        inputFields.get(3).click();
        $x("//div[@class='DayPicker-Day'] [contains(@aria-label,'" + data + "')]")
                .shouldBe(Condition.visible).click();
        selectButton.click();
    }

    public void setValueInFieldWhoIsFlying(int adultPassengers, boolean economy) {
        inputFields.get(4).click();
        SelenideElement economyOrBusiness = economy ? economyButton : businessButton;
        economyOrBusiness.shouldBe(Condition.visible).click();

        int count = 1;
        while (count < adultPassengers) {
            plusPassengerButton.click();
            count++;
        }
    }

    public TicketSearchResultPage findTickets() {
        submitButton.click();
        return new TicketSearchResultPage();
    }
}
