package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTests {
    @BeforeAll
    static void beforeall() {
        Configuration.startMaximized = true;
        Configuration.pageLoadTimeout = 600000;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Darya");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("ivanova@mail.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("89084567443");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2020");
        $(".react-datepicker__day--015:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("History").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/foto.png");
        $("#currentAddress").setValue("street Moskovskay, 23");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Darya Ivanova"), text("ivanova@mail.ru"), text("Female"));
        $(".table-responsive").shouldHave(text("8908456744"), text("15 May,2020"), text("History"));
        $(".table-responsive").shouldHave(text("Sports"), text("foto.png"), text("street Moskovskay, 23"));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));
    }
}
