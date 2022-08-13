package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DataGeneratorForm;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class DeliveryTest {



    @Test
    void shouldRegisterCard() {
        open("http://localhost:9999");
        DataGeneratorForm firstAttempt = DataGenerator.Registration.generateByForm("ru");
        String firstDate = DataGenerator.generateDate(27);
        String secondDate = DataGenerator.generateDate(35);

        //первая попытка

        $("[data-test-id='city'] input").setValue(DataGenerator.city());
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstDate);
        $("[data-test-id='name'] input").setValue(firstAttempt.getName());
        $("[data-test-id='phone'] input").setValue(firstAttempt.getPhone());
        $("[data-test-id='agreement'] .checkbox__text").click();
        $$(".button__text").find(Condition.text("Запланировать")).click();
        $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Встреча успешно запланирована на " + firstDate), Duration.ofSeconds(15));
        $(".notification__content").click();

        //вторая попытка

        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondDate);
        $$(".button__text").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content").shouldBe(Condition.visible).shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").find(exactText("Перепланировать")).click();
        $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Встреча успешно запланирована на " + secondDate), Duration.ofSeconds(15));
    }
}
