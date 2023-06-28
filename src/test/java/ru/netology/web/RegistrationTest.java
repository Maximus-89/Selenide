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

    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
        $$(".tab-item").find(exactText("По номеру счёта")).click();
        $$("[name='number']").last().setValue("4055 0100 0123 4613 8564");
        $$("[name='phone']").last().setValue("+792000000000");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Успешная авторизация")).shouldBe(visible, Duration.ofSeconds(5));
        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(5));
    }
}

