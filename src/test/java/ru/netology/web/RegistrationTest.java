package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");
        $("[data-test-id=city']").setValue("Ставрополь");
        $("[placeholder= 'Дата встречи']").setValue("01.07.2023");
        $("[name= 'name']").setValue("Крепышев Василий");
        $("[name='phone']").setValue("+79200000000");
        $$("data-test-id=agreement").find(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Встреча успешно забронирована на 01.07.2023")).shouldBe(visible, Duration.ofSeconds(15));
    }
}

